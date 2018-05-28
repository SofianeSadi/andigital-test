package test.andigital.sofianesadi.daos.impl;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.ICustomerRepository;
import test.andigital.sofianesadi.entities.CustomerEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kata number repository implementation.
 * This implementation use a static list containing 4 customers as datasource.
 */
@Repository
public class KataCustomerRepositoryImpl implements ICustomerRepository {
    static List<CustomerEntity> customerDataSource = Arrays.asList(
            new CustomerEntity("1"),
            new CustomerEntity("2"),
            new CustomerEntity("3"),
            new CustomerEntity("4")
    );

    /**
     * @inheritDoc
     */
    @Override
    public Mono<CustomerEntity> findById(String customerId) {
        return Mono.justOrEmpty(
                customerDataSource
                        .parallelStream()
                        .filter(customer -> customer.getId().equals(customerId))
                        .findFirst()
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Flux<CustomerEntity> findAll() {
        return Flux.fromIterable(
                customerDataSource
                        .parallelStream()
                        .collect(Collectors.toList())
        );
    }
}
