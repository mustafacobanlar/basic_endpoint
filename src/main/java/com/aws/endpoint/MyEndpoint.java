package com.aws.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyEndpoint {

    @GetMapping("/status")
    public String getStatus() {
        return "Application is active.";
    }
}
