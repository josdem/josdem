package com.jos.dem.chatbot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BotsWebSocketHandler implements WebSocketHandler {

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session.send(
        session
            .receive()
            .map(webSocketMessage -> webSocketMessage.getPayloadAsText())
            .log()
            .map(message -> session.textMessage(message)));
  }
}
