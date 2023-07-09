package com.aws.endpoint.controller;

import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class MediaController {

    private static final String UPLOAD_DIR = "/home/ec2-user/videos";

    @PutMapping("/animation")
    public String uploadMedia(@RequestBody byte[] mediaBytes) {
        try {
            // Create a unique file name
            String fileName = "temp" + ".mp4";

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

}

