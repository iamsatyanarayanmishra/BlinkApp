package com.example.blink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enable simple broker for public topics and user-specific destinations
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app"); // Prefix for client-to-server messages
        config.setUserDestinationPrefix("/user"); // Prefix for user-specific destinations
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register WebSocket endpoint for clients to connect
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}