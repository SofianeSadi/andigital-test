package test.andigital.sofianesadi.daos.impl;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.entities.CustomerEntity;
import test.andigital.sofianesadi.entities.NumberEntity;
import test.andigital.sofianesadi.entities.NumberStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Kata number repository implementation.
 * This implementation use a static list as datasource.
 */
@Repository
public class KataNumberRepositoryImpl implements INumberRepository {
    /**
     * Static datasource
     * 9 numbers: 3 per customer
     */
    private static List<NumberEntity> numberDataSource = Arrays.asList(
            new NumberEntity(1L, "07492525820", new CustomerEntity(1L), NumberStatus.DESACTIVATED),
            new NumberEntity(2L, "07492525821", new CustomerEntity(2L), NumberStatus.DESACTIVATED),
            new NumberEntity(3L, "07492525822", new CustomerEntity(3L), NumberStatus.DESACTIVATED),
            new NumberEntity(4L, "07492525823", new CustomerEntity(1L), NumberStatus.DESACTIVATED),
            new NumberEntity(5L, "07492525824", new CustomerEntity(2L), NumberStatus.DESACTIVATED),
            new NumberEntity(6L, "07492525825", new CustomerEntity(3L), NumberStatus.DESACTIVATED),
            new NumberEntity(7L, "07492525826", new CustomerEntity(1L), NumberStatus.DESACTIVATED),
            new NumberEntity(8L, "07492525827", new CustomerEntity(2L), NumberStatus.DESACTIVATED),
            new NumberEntity(9L, "07492525828", new CustomerEntity(3L), NumberStatus.DESACTIVATED)
    );

    /**
     * @inheritDoc
     */
    @Override
    public Flux<NumberEntity> findAll() {
        return Flux.fromIterable(numberDataSource);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Mono<NumberEntity> findById(Long id) {
        return Mono.justOrEmpty(
                numberDataSource
                        .stream()
                        .filter(number -> number.getId().equals(id))
                        .findFirst()
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Flux<NumberEntity> findByCustomerId(Long customerId) {
        return Flux.fromIterable(
                Objects.requireNonNull(
                        numberDataSource
                                .stream()
                                .filter(number -> number.getCustomerEntity().getId().equals(customerId))
                                .collect(Collectors.toList())
                ));
    }

    /**
     * @inheritDoc
     */
    @Override
    public Mono<NumberEntity> save(NumberEntity numberEntity) {
        return Mono.just(numberEntity);
    }
}
