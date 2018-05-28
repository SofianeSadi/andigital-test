package test.andigital.sofianesadi.handlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.daos.impl.KataCustomerRepositoryImpl;
import test.andigital.sofianesadi.entities.NumberEntity;
import test.andigital.sofianesadi.entities.NumberStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NumberHandlerAndRouterTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private WebTestClient webTestClient;

    /**
     * Test list all numbers
     */
    @Test
    void testListNumbers() {
        webTestClient.get()
                .uri("/numbers")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBodyList(NumberEntity.class).hasSize(9); // Should return 4 customers
    }

    /**
     * Test get a single number.
     */
    @Test
    void testGetNumber() {
        // Test customer 1: 3 numbers
        webTestClient.get()
                .uri("/numbers/1")
                .exchange()
                .expectStatus().isOk() // Expect a 200
                .expectHeader().contentType(MediaType.APPLICATION_JSON) // Body type should be APPLICATION_JSON
                .expectBody(NumberEntity.class); // Should return 3 customers

        // Test customer 1: 3 numbers
        webTestClient.get()
                .uri("/numbers/100")
                .exchange()
                .expectStatus().isNotFound(); // Expect a 404
    }


    /**
     * Test get a non existing number.
     */
    @Test
    void testGetNumberNotFound() {
        // Test customer 1: 3 numbers
        webTestClient.get()
                .uri("/numbers/100")
                .exchange()
                .expectStatus().isNotFound(); // Expect a 404
    }

    @Test
    void testUpdateNumber() {
        NumberEntity numberEntity = new NumberEntity("1", null, null, NumberStatus.ACTIVATED);

        // Valid id with good entity
        webTestClient.put().uri("/numbers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(numberEntity), NumberEntity.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.status").isEqualTo("ACTIVATED");

    }

    @Test
    void testUpdateNumberNonExisting() {
        NumberEntity numberEntity = new NumberEntity("1", null, null, NumberStatus.ACTIVATED);
        // Invalid id with good entity
        webTestClient.put().uri("/numbers/100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(numberEntity), NumberEntity.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateNumberWrongBody() {
        // Valid id with no entity
        webTestClient.put().uri("/numbers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
