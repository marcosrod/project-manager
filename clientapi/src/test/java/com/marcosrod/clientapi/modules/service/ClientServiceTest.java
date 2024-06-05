package com.marcosrod.clientapi.modules.service;

import com.marcosrod.clientapi.modules.client.repository.ClientRepository;
import com.marcosrod.clientapi.modules.client.service.ClientService;
import com.marcosrod.clientapi.modules.common.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.marcosrod.clientapi.modules.client.enums.ClientError.DUPLICATED_NAME;
import static com.marcosrod.clientapi.modules.helper.ClientHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService service;
    @Mock
    private ClientRepository repository;

    @Test
    void save_shouldReturnClientResponse_whenValidNameSent() {
        var clientWithoutId = getClientWithoutId();
        var clientName = clientWithoutId.getName();
        doReturn(getClient()).when(repository).save(getClientWithoutId());
        doReturn(false).when(repository).existsByName(clientName);

        assertThat(service.save(getClientRequest()))
                .isEqualTo(getClientResponse());

        verify(repository).existsByName(clientName);
        verify(repository).save(clientWithoutId);
    }

    @Test
    void save_shouldReturnThrowException_whenDuplicatedNameSent() {
        var request = getClientRequest();
        var clientName = request.name();
        doReturn(true).when(repository).existsByName(clientName);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.save(request))
                        .withMessage(DUPLICATED_NAME.getErrorMessage());

        verify(repository).existsByName(clientName);
        verify(repository, never()).save(any());
    }


}
