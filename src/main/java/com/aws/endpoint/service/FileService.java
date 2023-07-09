package com.aws.endpoint.service;

import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class FileService {
    public void deleteFiles(String directoryPath) {

        String dir = "/home/ec2-user/";

        File directory = new File("" + dir + directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory path");
            return;
        }

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                    System.out.println("Deleted file: " + file.getName());
                }
            }
        } else {
            System.out.println("No files found in the directory");
        }
    }
}
