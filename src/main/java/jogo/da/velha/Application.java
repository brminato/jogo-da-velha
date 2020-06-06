package jogo.da.velha;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Jogo da Velha",
                version = "1.0.0",
                description = "Jogo da Velha API",
                contact = @Contact(url = "http://localhost:8080", name = "BRMINATO", email = "brminato@gmail.com")
        )
)
public class Application {

    public static void main (String[] args) {
        Micronaut.run(Application.class);
    }
}