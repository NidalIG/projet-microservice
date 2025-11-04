package com.example.apiclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiClientApplication {

    @Value("${gateway.url}")
    private String gatewayUrl;

    public static void main(String[] args) {
        SpringApplication.run(ApiClientApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            System.out.println("Gateway URL: " + gatewayUrl);

            testCreateProduct(restTemplate);
            testGetProduct(restTemplate);
            testCreateProductAsUser(restTemplate);
            testGetComposite(restTemplate);
        };
    }

    private void testCreateProduct(RestTemplate restTemplate) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("username", "admin");
            headers.set("password", "admin123");
            headers.set("role", "ADMIN");

            String productJson = """
                {
                    "name": "Laptop Dell XPS 15",
                    "weight": 2.5
                }
                """;

            HttpEntity<String> request = new HttpEntity<>(productJson, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    gatewayUrl + "/products",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void testGetProduct(RestTemplate restTemplate) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("username", "user");
            headers.set("password", "user123");
            headers.set("role", "USER");

            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    gatewayUrl + "/products/1",
                    HttpMethod.GET,
                    request,
                    String.class
            );

            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void testCreateProductAsUser(RestTemplate restTemplate) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("username", "user");
            headers.set("password", "user123");
            headers.set("role", "USER");

            String productJson = """
            {
                "name": "Test Product",
                "weight": 1.0
            }
            """;

            HttpEntity<String> request = new HttpEntity<>(productJson, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    gatewayUrl + "/products",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            System.out.println(" ERREUR: USER a pu créer un produit !");
            System.out.println("Status: " + response.getStatusCode());

        } catch (Exception e) {
            // C'est ce qu'on attend !
            System.out.println(" Erreur attendue (USER ne peut pas POST): " + e.getMessage());
        }
    }

    private void testGetComposite(RestTemplate restTemplate) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("username", "admin");
            headers.set("password", "admin123");
            headers.set("role", "ADMIN");

            HttpEntity<String> request = new HttpEntity<>(headers);

            // Utilisez l'ID 1 qui existe dans votre base
            ResponseEntity<String> response = restTemplate.exchange(
                    gatewayUrl + "/products-composite/1",
                    HttpMethod.GET,
                    request,
                    String.class
            );

            System.out.println("Composite Status: " + response.getStatusCode());
            System.out.println(" Composite Response: " + response.getBody());
        } catch (Exception e) {
            System.out.println(" Erreur composite: " + e.getMessage());
        }
    }
}
