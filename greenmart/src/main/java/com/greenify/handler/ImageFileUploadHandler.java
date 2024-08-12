package com.greenify.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class ImageFileUploadHandler {

	@Value("${image.upload.dir}")
    private String uploadDirPath;
	
	public String uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(uploadDirPath, fileName);
            try {
				Files.createDirectories(filePath.getParent());
	            image.transferTo(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            return "/" + uploadDirPath + fileName;
        }
		return null;

	}
	
}
