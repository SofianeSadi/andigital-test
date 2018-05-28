package test.andigital.sofianesadi.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import test.andigital.sofianesadi.handlers.CustomerHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * Customer actions router
 * Available endpoints:
 * GET /customers: Return the list of customers.
 * GET /customers/[id]: Return the customer matching the given [id] param.
 * GET /customers/[id]/numbers: Return the list of numbers for the customer given [id] param.
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
