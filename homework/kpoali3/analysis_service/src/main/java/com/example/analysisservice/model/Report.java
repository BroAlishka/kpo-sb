package com.example.analysisservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long submissionId;
    private String assignmentId;
    private String student;
    private boolean plagiarized;
    private String status;
    private Instant analyzedAt;
    private String fileHash;

    public Report() {}

    public Report(Long submissionId, String assignmentId, String student, boolean plagiarized, String status, Instant analyzedAt, String fileHash) {
        this.submissionId = submissionId;
        this.assignmentId = assignmentId;
        this.student = student;
        this.plagiarized = plagiarized;
        this.status = status;
        this.analyzedAt = analyzedAt;
        this.fileHash = fileHash;
    }

    public Long getId() { return id; }
    public Long getSubmissionId() { return submissionId; }
    public String getAssignmentId() { return assignmentId; }
    public String getStudent() { return student; }
    public boolean isPlagiarized() { return plagiarized; }
    public String getStatus() { return status; }
    public Instant getAnalyzedAt() { return analyzedAt; }
    public String getFileHash() { return fileHash; }

    public void setSubmissionId(Long submissionId) { this.submissionId = submissionId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }
    public void setStudent(String student) { this.student = student; }
    public void setPlagiarized(boolean plagiarized) { this.plagiarized = plagiarized; }
    public void setStatus(String status) { this.status = status; }
    public void setAnalyzedAt(Instant analyzedAt) { this.analyzedAt = analyzedAt; }
    public void setFileHash(String fileHash) { this.fileHash = fileHash; }
}
