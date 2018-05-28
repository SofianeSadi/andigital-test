package test.andigital.sofianesadi.daos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.entities.CustomerEntity;

public interface ICustomerRepository {
    /**
     * Find all customer
     * @return Flux containing all the customer in the system.
     */
    Flux<CustomerEntity> findAll();

    /**
     * Find a customer by it's ID
     * @param customerId The customer ID to match.
     * @return Mono containing the customer if found, empty mono otherwise.
     */
    Mono<CustomerEntity> findById(String customerId);
}
