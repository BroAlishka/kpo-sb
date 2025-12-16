package com.example.analysisservice.service;

import com.example.analysisservice.model.Report;
import com.example.analysisservice.repository.ReportRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {
    private final ReportRepository repository;
    private final FileMetadataClient fileClient;

    public ReportService(ReportRepository repository, FileMetadataClient fileClient) {
        this.repository = repository;
        this.fileClient = fileClient;
    }

    @Transactional
    public Report analyze(Long submissionId) {
        FileMetadataClient.SubmissionMetadata meta = fileClient.fetchSubmission(submissionId);
        boolean plagiarized = repository.findFirstByFileHashAndSubmissionIdNot(meta.fileHash, submissionId).isPresent();
        Report report = new Report(
                submissionId,
                meta.assignmentId,
                meta.student,
                plagiarized,
                "COMPLETED",
                Instant.now(),
                meta.fileHash
        );
        return repository.save(report);
    }

    public List<Report> byAssignment(String assignmentId) {
        return repository.findByAssignmentId(assignmentId);
    }
}
