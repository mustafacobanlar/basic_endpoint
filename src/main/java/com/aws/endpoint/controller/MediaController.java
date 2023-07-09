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
public class MediaController {

    private static final String UPLOAD_DIR = "/home/ec2-user/videos";

    @PutMapping("/animation")
    public String uploadMedia(@RequestBody byte[] mediaBytes) {
        try {
            // Create a unique file name
            String fileName = "video" + ".mp4";

            // Define the path where the file will be saved
            String filePath = UPLOAD_DIR + "/" + fileName;

            // Save the media file to the specified path
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(mediaBytes);
            fos.close();

            // Return a success response
            return "Media uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            // Return an error response
            return "Failed to upload media";
        }
    }
    @GetMapping("/download/animation")
    public ResponseEntity<byte[]> downloadImage() {
        try {
            // Define the path of the image file
            String filePath = UPLOAD_DIR + "/video.mp4";

            // Read the image file as a byte array
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] imageBytes = StreamUtils.copyToByteArray(fis);
            fis.close();

            // Create a response with the image bytes and appropriate headers
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            // Return an error response
            return ResponseEntity.notFound().build();
        }
    }

}

