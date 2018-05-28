package test.andigital.sofianesadi.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.entities.NumberEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * This handler responsibility concern the numbers management.
 *  List all numbers
 *  Get number by ID.
 *  Update a number.
 */
@Component
public class NumberHandler {
    private final INumberRepository numberRepository;

    @Autowired
    public NumberHandler(INumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    /**
     * Return all numbers
     * @return 200 response with number list if found, 404 otherwise.
     */
    public Mono<ServerResponse> listNumbers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.numberRepository.findAll(), NumberEntity.class);
    }

    /**
     * Get a number
     * @param request Server request containing a url parameter "id"
     * @return Number if found, 404 otherwise.
     */
    public Mono<ServerResponse> getNumber(ServerRequest request) {
        return this.numberRepository
                .findById(request.pathVariable("id"))
                .flatMap(foundNumber -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromObject(foundNumber)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Update a number by it's ID.
     * This function will only update the status property to activate the number.
     * @return New number if updated, 404 otherwise.
     */
    public Mono<ServerResponse> updateNumber(ServerRequest request) {
        Mono<NumberEntity> numberMono = request.bodyToMono(NumberEntity.class);
        Mono<NumberEntity> updatedNumber = numberMono
                .flatMap(newNumberProperty ->
                        this.numberRepository.findById(request.pathVariable("id")) // find the number.
                        .flatMap(existingNumber -> {
                            existingNumber.setStatus(newNumberProperty.getStatus()); // update the number.
                            return Mono.justOrEmpty(existingNumber);
                        }));
        return updatedNumber
                .flatMap(numberResponse -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(numberResponse)))  // if the number has been updated return the updated number.
                .switchIfEmpty(ServerResponse.notFound().build()); // if the number has not been found return a 404.
    }
}
