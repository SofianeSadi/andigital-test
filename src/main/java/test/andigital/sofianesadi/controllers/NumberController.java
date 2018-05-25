package test.andigital.sofianesadi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.entities.NumberEntity;
import test.andigital.sofianesadi.entities.NumberStatus;

@RestController
@RequestMapping("/numbers")
public class NumberController {
    private final INumberRepository kataNumberRepositoryImpl;

    @Autowired
    public NumberController(INumberRepository kataNumberRepositoryImpl) {
        this.kataNumberRepositoryImpl = kataNumberRepositoryImpl;
    }

    @GetMapping
    public Flux<NumberEntity> getAllNumbers() {
        return kataNumberRepositoryImpl.findAll();
    }

    @PatchMapping("{id}/activate")
    public Mono<ResponseEntity<NumberEntity>> activateNumber(@PathVariable(value = "id") Long id) {
        return kataNumberRepositoryImpl
                .findById(id)
                .flatMap(foundNumber -> {
                    foundNumber.setStatus(NumberStatus.ACTIVATED);
                    return kataNumberRepositoryImpl.save(foundNumber);
                })
                .map(updatedNumber -> new ResponseEntity<>(updatedNumber, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
