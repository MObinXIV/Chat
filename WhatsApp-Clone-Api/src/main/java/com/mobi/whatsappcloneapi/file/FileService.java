package com.mobi.whatsappcloneapi.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.*;
import static java.lang.System.*;

@Service
@Slf4j
public class FileService {
    @Value("${application.file.uploads.media-output-path}")
    private String fileUploadPath;
    public String saveFile(@NonNull MultipartFile sourceFile,
                           @NonNull String userId) {
        final String fileUploadSubPath = "users" + separator + userId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile,
                             @NonNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);

        if(!targetFolder.exists()){
            boolean folderCreated = targetFolder.mkdirs();
            if(!folderCreated){
                log.warn("Failed to create directory: {}", targetFolder);
                return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        final String targetFilePath = finalUploadPath + separator + currentTimeMillis()  + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File was saved successfully: {}", targetFilePath);
            return targetFilePath;
        } catch (Exception e) {
            log.error("Failed was not saved", e);
        }
        return null;
    }

    private String getFileExtension(String filename) {
        if(filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if(lastDotIndex == -1 ) {
            return ""; // No extension found
        }
        return filename.substring(lastDotIndex + 1).toLowerCase();
    }
}
