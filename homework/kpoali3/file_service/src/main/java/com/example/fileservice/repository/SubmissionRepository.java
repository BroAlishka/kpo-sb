package com.example.fileservice.repository;

import com.example.fileservice.model.Submission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentId(String assignmentId);
}
