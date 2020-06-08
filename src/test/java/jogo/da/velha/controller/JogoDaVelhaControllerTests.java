package jogo.da.velha.controller;

import javax.inject.Inject;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Flowable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class JogoDaVelhaControllerTests {

    @Inject
    @Client("/")
    private RxHttpClient client;

    @Test
    public void response200Test () {
        Flowable<HttpResponse<String>> call = client
                .exchange(HttpRequest.POST("/jogovelha", "{\"jogo\": [\"XXX\", \"OOX\", \"XOX\"]}"), String.class);

        HttpResponse<String> response = call.blockingFirst();

        Assertions.assertEquals(200, response.getStatus().getCode());
    }

    @Test
    public void response404Test () {
        Flowable<HttpResponse<String>> call = client
                .exchange(HttpRequest.POST("/jogovelha", "{\"jogo\": [\"XXO\", \"OOX\", \"XOX\"]}"), String.class);

        try {
            HttpResponse<String> response = call.blockingFirst();
        } catch (HttpClientResponseException e) {
            Assertions.assertEquals(404, e.getStatus().getCode());
        }
    }

    @Test
    public void response400Test () {
        Flowable<HttpResponse<String>> call = client
                .exchange(HttpRequest.POST("/jogovelha", "{\"jogo\": [\"XX\", \"OOX\", \"XOX\"]}"), String.class);

        try {
            HttpResponse<String> response = call.blockingFirst();
        } catch (HttpClientResponseException e) {
            Assertions.assertEquals(400, e.getStatus().getCode());
        }
    }

}
