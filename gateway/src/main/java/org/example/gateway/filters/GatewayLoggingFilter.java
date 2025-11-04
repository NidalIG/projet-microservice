//package org.example.gateway.filters;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;

//@Component
//public class GatewayLoggingFilter implements GlobalFilter, Ordered {
//
//    private static final Logger logger = LoggerFactory.getLogger(GatewayLoggingFilter.class);
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // --- Pre Filter ---
//        String requestUri = exchange.getRequest().getURI().toString();
//        logger.info("[PRE FILTER] URI de la requête : {}", requestUri);

//        return chain.filter(exchange)
//                .doOnSuccess(aVoid -> {
//                    // --- Post Filter ---
//                    int statusCode = exchange.getResponse().getStatusCode() != null
//                            ? exchange.getResponse().getStatusCode().value()
//                            : 0;
//                    logger.info("[POST FILTER] Code de réponse : {}", statusCode);
//                });
//    }
//
//    @Override
//    public int getOrder() {
//        return -1; // s’exécute en premier
//    }
//}
