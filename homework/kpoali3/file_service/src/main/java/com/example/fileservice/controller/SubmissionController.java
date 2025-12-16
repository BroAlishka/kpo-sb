package com.example.fileservice.controller;

import com.example.fileservice.model.Submission;
import com.example.fileservice.repository.SubmissionRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HexFormat;
import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SubmissionController {

    private final SubmissionRepository repository;
    private final Path storageRoot;

    public SubmissionController(SubmissionRepository repository) throws IOException {
        this.repository = repository;
        this.storageRoot = Path.of("/data/files");
        Files.createDirectories(storageRoot);
    }

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Submission uploadSubmission(@RequestParam("file") MultipartFile file,
                                       @RequestParam("student") String student,
                                       @RequestParam("assignmentId") String assignmentId) throws IOException, NoSuchAlgorithmException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String storedName = Instant.now().toEpochMilli() + "-" + filename;
        Path destination = storageRoot.resolve(storedName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        String hash = sha256(destination);
        Submission submission = new Submission(student, assignmentId, filename, storedName, hash, Instant.now());
        return repository.save(submission);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Submission submission = repository.findById(id).orElseThrow();
        Path filePath = storageRoot.resolve(submission.getStoredFilename());
        FileSystemResource resource = new FileSystemResource(filePath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + submission.getOriginalFilename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/files/{id}/metadata")
    public Submission metadata(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping("/assignments/{assignmentId}/submissions")
    public List<Submission> assignmentSubmissions(@PathVariable String assignmentId) {
        return repository.findByAssignmentId(assignmentId);
    }

    private String sha256(Path file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(Files.readAllBytes(file));
        return HexFormat.of().formatHex(digest.digest());
    }
}
