package com.n3.project_thoitrang.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
    String uploadLocal(MultipartFile file);

    String uploadFirebase(String localPath);
}
