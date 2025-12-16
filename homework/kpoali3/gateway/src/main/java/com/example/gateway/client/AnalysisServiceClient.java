package com.example.gateway.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AnalysisServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${analysis.service.url:http://analysis-service:8082}")
    private String analysisServiceUrl;

    public Report analyze(Long submissionId) {
        String url = analysisServiceUrl + "/reports/analyze?submissionId=" + submissionId;
        ResponseEntity<Report> response = restTemplate.postForEntity(url, null, Report.class);
        return response.getBody();
    }

    public List<Report> reportsForAssignment(String assignmentId) {
        String url = analysisServiceUrl + "/assignments/" + assignmentId + "/reports";
        Report[] reports = restTemplate.getForObject(url, Report[].class);
        if (reports == null) {
            return List.of();
        }
        return Arrays.asList(reports);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Report {
        public Long id;
        public Long submissionId;
        public String assignmentId;
        public String student;
        public boolean plagiarized;
        public String status;
    }
}
