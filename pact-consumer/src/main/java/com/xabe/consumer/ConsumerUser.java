package com.xabe.consumer;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class ConsumerUser {

    private HttpClient httpClient;
    private Jsonb jsonb;

    public ConsumerUser() {
        this.jsonb = JsonbBuilder.create();
        this.httpClient = createHttpClient();
    }

    public User getUser(int id) throws IOException, InterruptedException {
        final var request = createHttpRequest(String.format("http://localhost:8888/user-service/user/%d",id));
        final var response = httpClient.send(request, JsonBodyHandler.jsonBodyHandler(jsonb, User.class));
        return response.body();
    }

    private HttpRequest createHttpRequest(String uri) {
        return HttpRequest
                .newBuilder(URI.create(uri))
                .timeout(Duration.ofMillis(5000))
                .GET()
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
                .setHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
                .build();
    }

    private HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)  // this is the default
                .connectTimeout(Duration.ofMillis(5000))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
    }
}
