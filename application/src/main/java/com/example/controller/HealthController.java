package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.constants.ApplicationConstants.HEALTH_CHECK_EP;
import static com.example.constants.ApplicationConstants.V1;

@RestController
@RequestMapping(path = V1)
public class HealthController {

    @GetMapping(HEALTH_CHECK_EP)
    public String healthCheck() {
        return " { \"Status\" : \"OK\" } ";
    }

}
