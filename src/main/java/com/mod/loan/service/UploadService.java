package com.mod.loan.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件到本地
 */
public interface UploadService {

    String uploadFile(String filePath, MultipartFile file);
}
