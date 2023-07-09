package com.aws.endpoint.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ImageController {

    private static final String UPLOAD_DIR = "/home/ec2-user/images";
    @PutMapping("/upload")
    public String uploadImage(@RequestBody byte[] imageBytes) {
        try {
            // Create a unique file name
            String fileName = System.currentTimeMillis() + ".jpg";

            // Define the path where the file will be saved
            String filePath = UPLOAD_DIR + "/" + fileName;

            // Save the image file to the specified path
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(imageBytes);
            fos.close();

            // Return a success response
            return "Image uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            // Return an error response
            return "Failed to upload image";
        }
    }
}
