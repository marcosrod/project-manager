package com.marcosrod.clientapi.modules.controller;

import com.marcosrod.clientapi.modules.client.service.ClientService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.marcosrod.clientapi.modules.common.helper.JsonHelper.asJsonString;
import static com.marcosrod.clientapi.modules.helper.ClientHelper.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ClientControllerTest {

    private static final String API_URI = "/api/clients";
    private static final String URI_EXISTS = API_URI + "/1" + "/exists";

    @MockBean
    private ClientService service;
    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void save_shouldReturnClientResponseAndOk_whenRequestSent() {
        var request = getClientRequest();
        var response = getClientResponse();
        doReturn(response).when(service).save(request);

        mvc.perform(MockMvcRequestBuilders.post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.name").value(response.name()));

        verify(service).save(request);
    }

    @SneakyThrows
    @Test
    void existsById_shouldReturnTrue_whenClientExists() {
        doReturn(true).when(service).existsById(TEST_NUMBER_ONE);

        mvc.perform(MockMvcRequestBuilders.get(URI_EXISTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(Boolean.TRUE));

        verify(service).existsById(TEST_NUMBER_ONE);
    }

    @SneakyThrows
    @Test
    void existsById_shouldReturnFalse_whenClientDoesNotExists() {
        doReturn(false).when(service).existsById(TEST_NUMBER_ONE);

        mvc.perform(MockMvcRequestBuilders.get(URI_EXISTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(Boolean.FALSE));

        verify(service).existsById(TEST_NUMBER_ONE);
    }


}
