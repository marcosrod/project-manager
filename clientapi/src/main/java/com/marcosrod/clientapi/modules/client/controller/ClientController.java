package com.marcosrod.clientapi.modules.client.controller;

import com.marcosrod.clientapi.modules.client.dto.ClientRequest;
import com.marcosrod.clientapi.modules.client.dto.ClientResponse;
import com.marcosrod.clientapi.modules.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    @PostMapping
    public ClientResponse save(@RequestBody ClientRequest request) {
        return service.save(request);
    }
}
