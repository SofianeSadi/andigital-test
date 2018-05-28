package test.andigital.sofianesadi.daos.impl;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.entities.NumberEntity;
import test.andigital.sofianesadi.entities.NumberStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kata number repository implementation.
 * This implementation use a static list containing 9 numbers as datasource.
 */
@Repository
public class KataNumberRepositoryImpl implements INumberRepository {
    /**
     * Static datasource
     * 9 numbers:
     *  3 numbers for customer 1 and 2
     *  2 numbers for customer 3
     *  1 number for customer 4
     */
    private static List<NumberEntity> NUMBERS_DATA_SOURCE = Arrays.asList(
            new NumberEntity("1", "07492525820", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(0), NumberStatus.DEACTIVATED),
            new NumberEntity("2", "07492525821", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(1), NumberStatus.DEACTIVATED),
            new NumberEntity("3", "07492525822", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(2), NumberStatus.DEACTIVATED),
            new NumberEntity("4", "07492525823", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(0), NumberStatus.DEACTIVATED),
            new NumberEntity("5", "07492525824", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(1), NumberStatus.DEACTIVATED),
            new NumberEntity("6", "07492525825", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(2), NumberStatus.DEACTIVATED),
            new NumberEntity("7", "07492525826", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(0), NumberStatus.DEACTIVATED),
            new NumberEntity("8", "07492525827", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(1), NumberStatus.DEACTIVATED),
            new NumberEntity("9", "07492525828", KataCustomerRepositoryImpl.CUSTOMERS_DATA_SOURCE.get(3), NumberStatus.DEACTIVATED)
    );

    /**
     * @inheritDoc
     */
    @Override
    public Flux<NumberEntity> findAll() {
        return Flux.fromIterable(NUMBERS_DATA_SOURCE);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Mono<NumberEntity> findById(String id) {
        return Mono.justOrEmpty(
                NUMBERS_DATA_SOURCE
                        .parallelStream()
                        .filter(number -> number.getId().equals(id))
                        .findFirst()
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Flux<NumberEntity> findByCustomerId(String customerId) {
        return Flux.fromIterable(
                NUMBERS_DATA_SOURCE
                        .parallelStream()
                        .filter(number -> number.getCustomerEntity().getId().equals(customerId))
                        .collect(Collectors.toList())
        );
    }
}
