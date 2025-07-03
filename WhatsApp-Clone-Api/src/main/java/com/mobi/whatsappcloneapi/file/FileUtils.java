package com.mobi.whatsappcloneapi.file;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {
    private FileUtils() {}

    public static byte[] readFileLocation(String fileUrl){
        if(StringUtils.isBlank(fileUrl))
        {
            return new byte[0];
        }

        try {
            Path file = new File(fileUrl).toPath();
            return Files.readAllBytes(file);
        } catch (IOException e) {
            log.warn("Failed to read file from location: {}", fileUrl);
        }
        return new byte[0];
    }
}
