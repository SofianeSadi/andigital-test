package test.andigital.sofianesadi.daos;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KataNumberRepositoryImplTest {
    @Autowired
    private INumberRepository kataNumberRepositoryImpl;

    /**
     * When findAll(): we expect to retrieve 9 numbers
     */
    @Test
    public void findAll() {
        StepVerifier.create(kataNumberRepositoryImpl.findAll())
                    .expectNextCount(9)
                    .verifyComplete();
    }

    /**
     * When findById with corrects number id: we expect to retrieve the number
     */
    @Test
    public void findById() {
        Flux.just(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L).map(id ->
                StepVerifier.create(kataNumberRepositoryImpl.findById(id))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    /**
     * When findById with an invalid id we expect nothing more than a complete
     */
    @Test
    public void findByIdNotExist() {
        StepVerifier.create(kataNumberRepositoryImpl.findById(10L)).verifyComplete();
    }

    /**
     * When findByCustomerId with valids ids we expect 3 numbers per customers
     */
    @Test
    public void findByCustomerId() {
        Flux.just(1L, 2L, 3L).map(id ->
                StepVerifier.create(kataNumberRepositoryImpl.findByCustomerId(id))
                        .expectNextCount(3)
                        .verifyComplete());
    }

    /**
     * When findByCUstomerId with invalid id we expect  numbers.
     */
    @Test
    public void findByCustomerIdNotExist() {
        Flux.just(4L).map(id ->
                StepVerifier.create(kataNumberRepositoryImpl.findByCustomerId(id))
                        .expectNextCount(0)
                        .verifyComplete());
    }
}
