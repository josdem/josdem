package com.jos.dem.chatbot.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jos.dem.chatbot.model.Event;
import com.jos.dem.chatbot.util.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class MessagesStreamerHandler implements WebSocketHandler {

  private Flux<String> intervalFlux;
  private final ObjectMapper mapper = new ObjectMapper();
  private final MessageGenerator messageGenerator;

  @PostConstruct
  private void setup() {
    intervalFlux = Flux.interval(Duration.ofSeconds(1)).map(it -> getEvent());
  }

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session.send(intervalFlux.map(session::textMessage));
  }

  private String getEvent() {
    JsonNode node = mapper.valueToTree(new Event(messageGenerator.generate(), Instant.now()));
    return node.toString();
  }
}
