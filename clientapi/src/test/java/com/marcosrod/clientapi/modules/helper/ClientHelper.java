package com.marcosrod.clientapi.modules.helper;

import com.marcosrod.clientapi.modules.client.dto.ClientRequest;
import com.marcosrod.clientapi.modules.client.dto.ClientResponse;
import com.marcosrod.clientapi.modules.client.model.Client;

public class ClientHelper {

    private static final String CLIENT_NAME = "john";

    public static ClientRequest getClientRequest() {
        return new ClientRequest(CLIENT_NAME);
    }

    public static Client getClient() {
        return new Client(1L, CLIENT_NAME);
    }

    public static Client getClientWithoutId() {
        return new Client(CLIENT_NAME);
    }

    public static ClientResponse getClientResponse() {
        return new ClientResponse(1L, CLIENT_NAME);
    }
}
