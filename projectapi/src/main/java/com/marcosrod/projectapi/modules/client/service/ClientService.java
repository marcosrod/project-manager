package com.marcosrod.projectapi.modules.client.service;

import com.marcosrod.projectapi.modules.common.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClientService {

    @Value("${app.config.services.client-api}")
    private String CLIENT_API_URI;

    public Boolean existsById(Long id) {
        return WebClient.create()
                .get()
                .uri(CLIENT_API_URI + id + "/exists")
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(error -> {
                    throw new ValidationException("Error searching for Client: ".concat(error.getMessage()));
                })
                .block();
    }
}
