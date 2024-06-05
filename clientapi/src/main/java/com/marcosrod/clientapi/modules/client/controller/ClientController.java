package com.marcosrod.clientapi.modules.client.controller;

import com.marcosrod.clientapi.modules.client.dto.ClientRequest;
import com.marcosrod.clientapi.modules.client.dto.ClientResponse;
import com.marcosrod.clientapi.modules.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    @PostMapping
    public ClientResponse save(@RequestBody ClientRequest request) {
        return service.save(request);
    }

    @GetMapping("{id}/exists")
    public boolean existsById(@PathVariable("id") Long id) {
        return service.existsById(id);
    }
}
