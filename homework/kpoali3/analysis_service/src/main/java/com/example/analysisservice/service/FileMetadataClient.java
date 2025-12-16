package com.example.analysisservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FileMetadataClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${file.service.url:http://file-service:8081}")
    private String fileServiceUrl;

    public SubmissionMetadata fetchSubmission(Long id) {
        String url = fileServiceUrl + "/files/" + id + "/metadata";
        ResponseEntity<SubmissionMetadata> response = restTemplate.getForEntity(url, SubmissionMetadata.class);
        return response.getBody();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubmissionMetadata {
        public Long id;
        public String student;
        public String assignmentId;
        public String originalFilename;
        public String storedFilename;
        public String fileHash;
        public Instant submittedAt;
    }
}
