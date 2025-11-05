package com.project0.websocket.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic");
    registry.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // with sockjs
     registry.addEndpoint("/ws-message").setAllowedOriginPatterns("*").withSockJS();
    // without sockjs
    //registry.addEndpoint("/ws-message").setAllowedOriginPatterns("*");
  }

  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedHeaders("*").allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH");
//    registry.addMapping("/**").allowCredentials(true)
//            .allowedHeaders("*")
//            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
//            .allowedOrigins("*");
  }
}
