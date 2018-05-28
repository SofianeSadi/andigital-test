package test.andigital.sofianesadi.daos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Kata number repository implementation test
 */
@SuppressWarnings("UnassignedFluxMonoInstance")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class KataNumberRepositoryImplTest {
    @Autowired
    private INumberRepository numberRepository;

    /**
     * When findAll(): we expect to retrieve 9 numbers
     */
    @Test
    void findAll() {
        StepVerifier.create(numberRepository.findAll())
                    .expectNextCount(9)
                    .verifyComplete();
    }

    /**
     * When findById with corrects number id: we expect to retrieve the number
     */
    @Test
    void findById() {
        Flux.just("1", "2", "3", "4", "5", "6", "7", "8", "9").map(id ->
                StepVerifier.create(numberRepository.findById(id))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    /**
     * When findById with an invalid id we expect nothing more than a complete
     */
    @Test
    void findByIdNotExist() {
        StepVerifier.create(numberRepository.findById("100"))
                .expectNextCount(0)
                .verifyComplete();
    }

    /**
     * When findByCustomerId with valids ids we expect 3 numbers per customers
     */
    @Test
    void findByCustomerId() {
    Flux.just("1", "2", "3").map(id ->
                StepVerifier.create(numberRepository.findByCustomerId(id))
                        .expectNextCount(3)
                        .verifyComplete());
    }

    /**
     * When findByCustomerId with invalid id we expect 0 numbers.
     */
    @Test
    void findByCustomerIdNotExist() {
        Flux.just("4").map(id ->
                StepVerifier.create(numberRepository.findByCustomerId(id))
                        .expectNextCount(0)
                        .verifyComplete());
    }
}
