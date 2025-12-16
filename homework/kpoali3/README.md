# Plagiarism checker microservices (Spring Boot)

This repository contains a three-service Spring Boot implementation that accepts student submissions, persists uploaded files, and detects plagiarism based on matching hashes across prior submissions.

## Services

### API Gateway (8080)
Routes client requests to the downstream services.
- `POST /submissions` — accepts `file`, `student`, and `assignmentId` multipart fields; stores the file via File Service and triggers plagiarism analysis; returns both submission metadata and the generated report.
- `GET /assignments/{assignmentId}/reports` — proxies report listings from Analysis Service.

### File Service (8081)
Stores uploaded works and exposes metadata.
- `POST /files` — multipart upload with `file`, `student`, `assignmentId`; saves file under `/data/files`, records metadata in H2, computes a SHA-256 hash.
- `GET /files/{id}` — download stored file.
- `GET /files/{id}/metadata` — fetch submission metadata (used by Analysis Service).
- `GET /assignments/{assignmentId}/submissions` — list submissions for an assignment.

### Analysis Service (8082)
Determines plagiarism by comparing hashes.
- `POST /reports/analyze?submissionId=` — fetches metadata from File Service, marks plagiarism if another submission with the same hash already has a report, and stores the result.
- `GET /assignments/{assignmentId}/reports` — list reports for an assignment.

## Plagiarism heuristic
A submission is marked plagiarized when its SHA-256 file hash matches a previously analyzed submission’s hash (different submission id). Hashes are supplied by the File Service metadata endpoint.

## Running locally

Prerequisites: Docker, Docker Compose.

```bash
docker compose up --build
```

Services will be available on ports 8080 (gateway), 8081 (file), and 8082 (analysis). Uploaded files are persisted under a Docker volume mounted at `/data/files` inside the File Service container.
