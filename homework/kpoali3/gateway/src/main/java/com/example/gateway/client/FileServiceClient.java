package com.example.gateway.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class FileServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${file.service.url:http://file-service:8081}")
    private String fileServiceUrl;

    public SubmissionResponse upload(byte[] bytes, String filename, String student, String assignmentId) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", new ByteArrayResource(bytes) {
            @Override
            public String getFilename() { return filename; }
        }, MediaType.APPLICATION_OCTET_STREAM);
        builder.part("student", student);
        builder.part("assignmentId", assignmentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, HttpEntity<?>> body = builder.build();
        HttpEntity<MultiValueMap<String, HttpEntity<?>>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(fileServiceUrl + "/files", request, SubmissionResponse.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubmissionResponse {
        public Long id;
        public String student;
        public String assignmentId;
        public String originalFilename;
        public String storedFilename;
        public String fileHash;
    }
}
