package com.example.analysisservice.controller;

import com.example.analysisservice.model.Report;
import com.example.analysisservice.service.ReportService;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/reports/analyze")
    public ResponseEntity<Report> analyze(@RequestParam @NotNull Long submissionId) {
        return ResponseEntity.ok(reportService.analyze(submissionId));
    }

    @GetMapping("/assignments/{assignmentId}/reports")
    public List<Report> reportsForAssignment(@PathVariable String assignmentId) {
        return reportService.byAssignment(assignmentId);
    }
}
