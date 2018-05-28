package test.andigital.sofianesadi.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import test.andigital.sofianesadi.controllers.CustomerHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * Customer actions router
 */
@Configuration
public class CustomerRouter {
    @Bean
    public RouterFunction<ServerResponse> customerRoutes(CustomerHandler customerHandler) {
        return RouterFunctions
                .route(GET("/customers"), customerHandler::listCustomer)
                .andRoute(GET("/customers/{id}"), customerHandler::getCustomer)
                .andRoute(GET("/customers/{id}/numbers"), customerHandler::getNumbers);
    }
}
