package com.aws.endpoint.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
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
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        try {
            // Define the path of the image file
            String filePath = UPLOAD_DIR + "/" + fileName;

            // Read the image file as a byte array
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] imageBytes = StreamUtils.copyToByteArray(fis);
            fis.close();

            // Create a response with the image bytes and appropriate headers
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            // Return an error response
            return ResponseEntity.notFound().build();
        }
    }

}
