package com.marcosrod.clientapi.modules.client.service;

import com.marcosrod.clientapi.modules.client.dto.ClientRequest;
import com.marcosrod.clientapi.modules.client.dto.ClientResponse;
import com.marcosrod.clientapi.modules.client.model.Client;
import com.marcosrod.clientapi.modules.client.repository.ClientRepository;
import com.marcosrod.clientapi.modules.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.marcosrod.clientapi.modules.client.enums.ClientError.DUPLICATED_NAME;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public ClientResponse save(ClientRequest request) {
        validateDuplicatedName(request.name());

        var savedClient = repository.save(Client.of(request));

        return new ClientResponse(savedClient.getId(), savedClient.getName());
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    private void validateDuplicatedName(String name) {
        if (repository.existsByName(name)) {
            throw new ValidationException(DUPLICATED_NAME.getErrorMessage());
        }
    }
}
