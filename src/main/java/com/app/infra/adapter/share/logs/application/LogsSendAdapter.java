package com.app.infra.adapter.share.logs.application;

import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.common.model.cqrs.ContextData;
import com.app.domain.share.logs.gateway.LogsSendGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Log4j2
@Service
public class LogsSendAdapter implements LogsSendGateway {



    @Override
    public Mono<Void> register(Command<Map<String, String>, ContextData> command) {

        var result = Mono.defer(() ->{
            var objectMapper = new ObjectMapper();
            String jsonData = null;
            jsonData = getString(command, objectMapper);
            log.info(jsonData);
            return Mono.empty();
        });

        return Mono.empty();
    }

    private static String getString(Command<Map<String, String>, ContextData> command, ObjectMapper objectMapper) {
        String jsonData;
        try {
            jsonData = objectMapper.writeValueAsString(command.payload());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonData;
    }
}
