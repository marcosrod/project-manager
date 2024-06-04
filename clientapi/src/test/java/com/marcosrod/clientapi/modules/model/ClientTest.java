package com.marcosrod.clientapi.modules.model;

import com.marcosrod.clientapi.modules.client.model.Client;
import org.junit.jupiter.api.Test;

import static com.marcosrod.clientapi.modules.helper.ClientHelper.getClientRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    public void of_shouldReturnClientWithName_whenCalled() {
        var clientRequest = getClientRequest();
        var client = Client.of(clientRequest);

        assertEquals(client.getName(), clientRequest.name());
    }
}
