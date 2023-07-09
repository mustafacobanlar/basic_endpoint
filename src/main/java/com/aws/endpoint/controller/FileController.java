package com.aws.endpoint.controller;

import com.aws.endpoint.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/delete/{folderName}")
    public String cleanDir(@PathVariable String folderName){
        fileService.deleteFiles(folderName);

        return folderName + " folder has been cleaned.";
    }

}
