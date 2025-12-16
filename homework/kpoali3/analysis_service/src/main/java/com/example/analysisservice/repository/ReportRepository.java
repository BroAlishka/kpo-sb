package com.example.analysisservice.repository;

import com.example.analysisservice.model.Report;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByAssignmentId(String assignmentId);
    Optional<Report> findFirstByFileHashAndSubmissionIdNot(String fileHash, Long submissionId);
}
