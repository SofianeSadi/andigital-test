package test.andigital.sofianesadi.daos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Kata customer repository implementation test
 */
@SuppressWarnings("UnassignedFluxMonoInstance")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class KataCustomerRepositoryImplTest {
    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * When findAll(): we expect to retrieve 4 customers
     */
    @Test
    void findAll() {
        StepVerifier.create(customerRepository.findAll())
                    .expectNextCount(4)
                    .verifyComplete();
    }

    /**
     * When findById with corrects number id: we expect to retrieve the customer
     */
    @Test
    void findById() {
        Flux.just("1", "2", "3", "4").map(id ->
                StepVerifier.create(customerRepository.findById(id))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    /**
     * When findById with an invalid id we expect nothing more than a complete
     */
    @Test
    void findByIdNotExist() {
        StepVerifier.create(customerRepository.findById("100"))
                .expectNextCount(0)
                .verifyComplete();
    }
}
