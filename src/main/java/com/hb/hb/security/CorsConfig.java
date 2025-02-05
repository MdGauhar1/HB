package com.hb.hb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow only your frontend URL
        config.addAllowedOrigin("http://localhost:3000"); // Replace with your frontend URL
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.setAllowCredentials(true); // Allow credentials like cookies
        config.addAllowedHeader("*"); // Allow any header

        // Register CORS configuration for all endpoints
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
