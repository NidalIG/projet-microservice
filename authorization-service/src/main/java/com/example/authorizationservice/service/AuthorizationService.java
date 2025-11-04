package com.example.authorizationservice.service;


import com.example.authorizationservice.client.ProductCompositeClient;
import lombok.extern.slf4j.Slf4j;
import com.example.authorizationservice.dto.AuthRequest;
import com.example.authorizationservice.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationService {
    private final ProductCompositeClient productCompositeClient;

    public AuthorizationService(ProductCompositeClient productCompositeClient) {
        this.productCompositeClient = productCompositeClient;
    }
    public boolean checkAccess(String username, String role) {
        return productCompositeClient.validateRole(username, role);
    }

    @Value("${security.productcomposite.admin.username}")
    private String adminUsername;

    @Value("${security.productcomposite.admin.password}")
    private String adminPassword;

    @Value("${security.productcomposite.user.username}")
    private String userUsername;

    @Value("${security.productcomposite.user.password}")
    private String userPassword;

    public AuthResponse validateCredentials(AuthRequest request) {
        log.info("Validating credentials for user: {} with role: {}", request.getUsername(), request.getRole());

        AuthResponse response = new AuthResponse();

        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            if (adminUsername.equals(request.getUsername()) && adminPassword.equals(request.getPassword())) {
                response.setAuthenticated(true);
                response.setRole("ADMIN");
                response.setMessage("Admin authenticated successfully");
            } else {
                response.setAuthenticated(false);
                response.setMessage("Invalid admin credentials");
            }
        } else if ("USER".equalsIgnoreCase(request.getRole())) {
            if (userUsername.equals(request.getUsername()) && userPassword.equals(request.getPassword())) {
                response.setAuthenticated(true);
                response.setRole("USER");
                response.setMessage("User authenticated successfully");
            } else {
                response.setAuthenticated(false);
                response.setMessage("Invalid user credentials");
            }
        } else {
            response.setAuthenticated(false);
            response.setMessage("Invalid role specified");
        }

        return response;
    }
}
