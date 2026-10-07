package com.example.register.controller;

import com.example.register.dto.RegisterRequest;
import com.example.register.dto.RegisterResponse;
import com.example.register.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Authcontroller{

    private final RegistrationService registrationService;

    public Authcontroller(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                registrationService.register(request)
        );
    }
}