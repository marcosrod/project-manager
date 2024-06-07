package com.marcosrod.clientapi.modules.helper;

import com.marcosrod.clientapi.modules.client.dto.ClientRequest;
import com.marcosrod.clientapi.modules.client.dto.ClientResponse;
import com.marcosrod.clientapi.modules.client.model.Client;

public class ClientHelper {

    private static final String CLIENT_NAME = "john";
    public static final Long TEST_NUMBER_ONE = 1L;

    public static ClientRequest getClientRequest() {
        return new ClientRequest(CLIENT_NAME);
    }

    public static Client getClient() {
        return new Client(TEST_NUMBER_ONE, CLIENT_NAME);
    }

    public static Client getClientWithoutId() {
        return new Client(CLIENT_NAME);
    }

    public static ClientResponse getClientResponse() {
        return new ClientResponse(TEST_NUMBER_ONE, CLIENT_NAME);
    }
}
