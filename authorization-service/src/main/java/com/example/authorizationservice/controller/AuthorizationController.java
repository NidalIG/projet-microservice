package com.example.authorizationservice.controller;


import lombok.RequiredArgsConstructor;
import com.example.authorizationservice.dto.AuthRequest;
import com.example.authorizationservice.dto.AuthResponse;
import com.example.authorizationservice.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/validate")
    public ResponseEntity<AuthResponse> validateCredentials(@RequestBody AuthRequest request) {
        AuthResponse response = authorizationService.validateCredentials(request);
        if (response.isAuthenticated()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }
}


