package test.andigital.sofianesadi.daos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.entities.NumberEntity;

/**
 * Number repository contract
 */
public interface INumberRepository {
    /**
     * Find all numbers
     * @return List of numbers if any, empty list otherwise.
     */
    Flux<NumberEntity> findAll();

    /**
     * Find a number by ID.
     * @param id The number ID
     * @return List of numbers if matches, empty list otherwise.
     */
    Mono<NumberEntity> findById(String id);

    /**
     * Find by customer id
     * @param customerId The customer ID
     * @return List of numbers for the given customer id if found, empty list otherwise.
     */
    Flux<NumberEntity> findByCustomerId(String customerId);
}
