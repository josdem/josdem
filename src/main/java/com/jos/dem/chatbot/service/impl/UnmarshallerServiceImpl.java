package com.jos.dem.chatbot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jos.dem.chatbot.service.UnmarshallerService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UnmarshallerServiceImpl implements UnmarshallerService {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public Map unmarshall(String json) {
    try {
      JsonNode jsonNode = mapper.readTree(json);
      return mapper.treeToValue(jsonNode, Map.class);
    } catch (JsonProcessingException jpe) {
      throw new RuntimeException(jpe.getMessage());
    }
  }
}
