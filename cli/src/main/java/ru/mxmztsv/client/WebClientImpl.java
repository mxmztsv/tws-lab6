package ru.mxmztsv.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mxmztsv.client.exception.ClientException;
import ru.mxmztsv.client.exception.ClientErrorDto;
import ru.mxmztsv.client.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebClientImpl {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;
    private final String COMMON_URI = "http://localhost:9091/api";

    public WebClientImpl() {
        httpClient = HttpClient.newBuilder().build();
    }

    public ClientSearchResponseDto search(
            String firstName, String lastName, Status status, Category category, String data
    ) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                        new URI(
                                COMMON_URI + "/clients/search"
                                        + buildRequestParams(firstName, lastName, status, category, data)
                        )
                )
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
        return MAPPER.readValue(
                httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body(),
                ClientSearchResponseDto.class
        );
    }

    public ClientResponseDto create(ClientRequestDto requestDto) throws URISyntaxException, IOException, InterruptedException, ClientException {
        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest request = HttpRequest.newBuilder(new URI(COMMON_URI + "/clients"))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return MAPPER.readValue(response.body().toString(), ClientResponseDto.class);
        }
        if (response.statusCode() >= 400) {
            ClientErrorDto errorDto = MAPPER.readValue(response.body().toString(), ClientErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
        return new ClientResponseDto();
    }

    public ClientResponseDto updateClient(Long id, ClientRequestDto requestDto) throws URISyntaxException, IOException, InterruptedException, ClientException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest request = HttpRequest.newBuilder(new URI(COMMON_URI + "/clients/" + id))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            ClientErrorDto errorDto = MAPPER.readValue(response.body().toString(), ClientErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
        return MAPPER.readValue(response.body().toString(), ClientResponseDto.class);
    }

    public void deleteClient(Long id) throws URISyntaxException, IOException, InterruptedException, ClientException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        HttpRequest request = HttpRequest.newBuilder(new URI(COMMON_URI + "/clients/" + id))
                .timeout(Duration.ofSeconds(5))
                .DELETE()
                .build();
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            ClientErrorDto errorDto = MAPPER.readValue(response.body().toString(), ClientErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
    }


    private String buildRequestParams(
            String firstName, String lastName, Status status, Category category, String data
    ) {
        StringBuilder params = new StringBuilder("?");
        if (firstName != null && !firstName.isEmpty()) params.append("&firstName=").append(firstName);
        if (lastName != null && !lastName.isEmpty()) params.append("&lastName=").append(lastName);
        if (status != null) params.append("&status=").append(status);
        if (category != null) params.append("&category=").append(category);
        if (data != null && !data.isEmpty()) params.append("&data=").append(data);
        return params.toString().equals("?") ? "" : params.toString();
    }

}
