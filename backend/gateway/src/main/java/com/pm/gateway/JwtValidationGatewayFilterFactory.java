package com.pm.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {


    private final String secret;

    public JwtValidationGatewayFilterFactory(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            String path = exchange.getRequest().getURI().getPath();
            if(path.contains("/auth")){
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest()
                    .getHeaders().getFirst("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try{
                String token = authHeader.replace("Bearer ", "");
                Claims claims = Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
                String userId = claims.get("userId", String.class);

                ServerHttpRequest request = exchange.getRequest()
                        .mutate()
                        .header("X-User-Id", userId)
                        .build();
                return chain.filter(
                        exchange.mutate()
                                .request(request)
                                .build()
                );
            }catch (Exception e){
                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }
}
