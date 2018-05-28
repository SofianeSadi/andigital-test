package test.andigital.sofianesadi.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import test.andigital.sofianesadi.controllers.NumberHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Number actions router
 */
@Configuration
public class NumberRouter {
    @Bean
    public RouterFunction<ServerResponse> numberRoutes(NumberHandler numberHandler) {
        return RouterFunctions
                .route(GET("/numbers").and(accept(APPLICATION_JSON)), numberHandler::listNumbers)
                .andRoute(GET("/numbers/{id}").and(accept(APPLICATION_JSON)), numberHandler::getNumber)
                .andRoute(PUT("/numbers/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), numberHandler::updateNumber);
    }
}
