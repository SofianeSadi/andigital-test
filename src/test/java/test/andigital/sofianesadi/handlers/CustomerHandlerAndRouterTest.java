package test.andigital.sofianesadi.handlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import test.andigital.sofianesadi.entities.NumberEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerHandlerAndRouterTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private WebTestClient webTestClient;

    /**
     * Test list all customers
     */
    @Test
    void testListCustomers() {
        webTestClient.get()
                .uri("/customers")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBodyList(NumberEntity.class).hasSize(4); // Should return 4 customers
    }

    /**
     * Test get all numbers for the asked customer.
     */
    @Test
    void testGetNumbers() {
        // Test customer 1: 3 numbers
        webTestClient.get()
                .uri("/customers/1/numbers")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBodyList(NumberEntity.class).hasSize(3); // Should return 3 customers

        // Test customer 2: 3 numbers
        webTestClient.get()
                .uri("/customers/2/numbers")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBodyList(NumberEntity.class).hasSize(3); // Should return 3 customers

        // Test customer 3: 2 numbers
        webTestClient.get()
                .uri("/customers/3/numbers")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBodyList(NumberEntity.class).hasSize(2); // Should return 2 customers

        // Test customer 4: 1 numbers
        webTestClient.get()
                .uri("/customers/4/numbers")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBodyList(NumberEntity.class).hasSize(1); // Should return 1 customers
    }

    /**
     * Test get all numbers for the asked customer.
     */
    @Test
    void testGetNumbersNotFound() {
        // Test customer 1: 3 numbers
        webTestClient.get()
                .uri("/customers/10/numbers")
                .exchange()
                .expectStatus().isNotFound(); // Expect a 404
    }
}
