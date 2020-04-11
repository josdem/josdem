package com.jos.dem.chatbot.config;

import com.jos.dem.chatbot.handler.BotsWebSocketHandler;
import com.jos.dem.chatbot.handler.MessagesStreamerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ChatbotConfig {

  private final BotsWebSocketHandler botsWebSocketHandler;
  private final MessagesStreamerHandler messagesStreamerHandler;

  @Bean
  public HandlerMapping handlerMapping() {
    Map<String, WebSocketHandler> map = new HashMap<>();
    map.put("/channel", botsWebSocketHandler);
    map.put("/messages", messagesStreamerHandler);

    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
    mapping.setUrlMap(map);
    return mapping;
  }

  @Bean
  public WebSocketHandlerAdapter handlerAdapter() {
    return new WebSocketHandlerAdapter();
  }
}
