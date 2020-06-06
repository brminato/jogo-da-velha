package jogo.da.velha.controller;

import java.util.HashMap;
import java.util.Map;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jogo.da.velha.controller.response.JogoDaVelhaResponse;
import jogo.da.velha.usecase.JogoDaVelhaUseCase;

@Controller
public class JogoDaVelhaController {

    private final JogoDaVelhaUseCase jogoDaVelhaUseCase;

    public JogoDaVelhaController (final JogoDaVelhaUseCase jogoDaVelhaUseCase) {
        this.jogoDaVelhaUseCase = jogoDaVelhaUseCase;
    }

    @Operation(summary = "Jogo da Velha", description = "Verifica se deu velha no jogo.")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = JogoDaVelhaResponse.class))),
            @ApiResponse(responseCode = "200", description = "Houve vencedor."),
            @ApiResponse(responseCode = "404", description = "Nao houve vencedor."),
            @ApiResponse(responseCode = "400", description = "Erro interno ou requisicao invalida.")
    })
    @Post(value = "/jogovelha", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<JogoDaVelhaResponse> isVelha (
            @RequestBody(description = "Jogo", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(example =
                    "{\n"
                            + "\"jogo\": [\"XXX\", \"OOX\", \"XOX\"]\n"
                            + "}\n"))) String[] jogo) {
        HttpResponse httpResponse = null;

        try {
            final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);
            final boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

            final JogoDaVelhaResponse jogoDaVelhaResponse = new JogoDaVelhaResponse();
            jogoDaVelhaResponse.setVelha(isVelha);

            if (isVelha) {
                httpResponse = HttpResponse.ok(jogoDaVelhaResponse);
            } else {
                httpResponse = HttpResponse.notFound(jogoDaVelhaResponse);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Erro interno ou requisicao invalida");
            httpResponse = HttpResponse.badRequest(response);
        }

        return httpResponse;
    }

}
