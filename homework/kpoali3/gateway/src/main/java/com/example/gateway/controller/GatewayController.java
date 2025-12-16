package com.example.gateway.controller;

import com.example.gateway.client.AnalysisServiceClient;
import com.example.gateway.client.FileServiceClient;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GatewayController {

    private final FileServiceClient fileClient;
    private final AnalysisServiceClient analysisClient;

    public GatewayController(FileServiceClient fileClient, AnalysisServiceClient analysisClient) {
        this.fileClient = fileClient;
        this.analysisClient = analysisClient;
    }

    @PostMapping(value = "/submissions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SubmissionResponse> submit(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("student") String student,
                                                     @RequestParam("assignmentId") String assignmentId) throws IOException {
        FileServiceClient.SubmissionResponse stored = fileClient.upload(file.getBytes(), file.getOriginalFilename(), student, assignmentId);
        AnalysisServiceClient.Report report = analysisClient.analyze(stored.id);
        return ResponseEntity.ok(new SubmissionResponse(stored, report));
    }

    @GetMapping("/assignments/{assignmentId}/reports")
    public List<AnalysisServiceClient.Report> reports(@PathVariable String assignmentId) {
        return analysisClient.reportsForAssignment(assignmentId);
    }

    public record SubmissionResponse(FileServiceClient.SubmissionResponse submission,
                                     AnalysisServiceClient.Report report) {}
}
