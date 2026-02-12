package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new RuntimeException("File name is empty. Please upload a valid file.");
        }

        if (!originalFileName.contains(".")) {
            throw new RuntimeException("Invalid file. File extension missing.");
        }

        String randomId = UUID.randomUUID().toString();

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String fileName = randomId + extension;

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = path + File.separator + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
}
