package test.andigital.sofianesadi.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.ICustomerRepository;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.entities.CustomerEntity;
import test.andigital.sofianesadi.entities.NumberEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Handler responsibility: Customers management
 *  List all customers
 *  Get all numbers for the specified customer.
 */
@Component
public class CustomerHandler {
    private ICustomerRepository customerRepository;
    private INumberRepository numberRepository;

    @Autowired
    public CustomerHandler(ICustomerRepository customerRepository, INumberRepository numberRepository) {
        this.customerRepository = customerRepository;
        this.numberRepository = numberRepository;
    }

    /**
     * List all customers in the system.
     * @param request Server request.
     * @return Response containing a list of all customers if any, empty list otherwise.
     */
    public Mono<ServerResponse> listCustomer(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.customerRepository.findAll(), CustomerEntity.class);
    }

    /**
     * Get a customer via it's id
     * @param request Server request: required param "id" as a Long
     * @return Customer if found, 404 otherwise.
     */
    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        return this.customerRepository.findById(request.pathVariable("id"))
                .flatMap(customer -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromObject(customer)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Get the numbers for the given customer id
     * @param request Server request
     * @return List of number for the customer if found, 404 otherwise.
     */
    public Mono<ServerResponse> getNumbers(ServerRequest request) {
        return this.customerRepository.findById(request.pathVariable("id"))
                .map(customer -> this.numberRepository.findByCustomerId(customer.getId()))
                .flatMap(customerNumbers -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(customerNumbers, NumberEntity.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
