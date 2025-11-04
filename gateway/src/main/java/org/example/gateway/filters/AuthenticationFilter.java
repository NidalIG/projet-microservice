package org.example.gateway.filters;




import lombok.extern.slf4j.Slf4j;
import org.example.gateway.dto.AuthRequest;
import org.example.gateway.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private WebClient.Builder webClientBuilder; // injection via champ

    public AuthenticationFilter() {
        super(Config.class); // obligatoire
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String username = exchange.getRequest().getHeaders().getFirst("username");
            String password = exchange.getRequest().getHeaders().getFirst("password");
            String role = exchange.getRequest().getHeaders().getFirst("role");

            if (username == null || password == null || role == null) {
                log.warn("Missing authentication headers");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:9081/auth/validate")
                    .bodyValue(new AuthRequest(username, password, role))
                    .retrieve()
                    .bodyToMono(AuthResponse.class)
                    .flatMap(authResponse -> {
                        if (authResponse.isAuthenticated()) {
                            return chain.filter(exchange);
                        } else {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    })
                    .onErrorResume(error -> {
                        log.error("Authorization service error: {}", error.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    public static class Config {
        // tu peux ajouter des options si besoin
    }
}