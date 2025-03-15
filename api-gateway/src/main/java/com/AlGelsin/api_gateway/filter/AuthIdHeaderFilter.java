package com.AlGelsin.api_gateway.filter;

import com.AlGelsin.api_gateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthIdHeaderFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;

    public AuthIdHeaderFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        System.out.println("Incoming request: " + exchange.getRequest().getURI());
        System.out.println("Existing Headers: " + headers);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Long authId = jwtUtil.getAuthIdFromToken(token);
                System.out.println("Extracted Auth-Id: " + authId);

                ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                        .header("Auth-Id", String.valueOf(authId))
                        .build();

                System.out.println("Yeni Header Eklendi: " + mutatedRequest.getHeaders().getFirst("Auth-Id"));

                ServerWebExchange mutatedExchange = exchange.mutate()
                        .request(mutatedRequest)
                        .build();

                return chain.filter(mutatedExchange);
            } catch (Exception e) {
                System.err.println("Error extracting Auth-Id: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}


