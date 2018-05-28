package test.andigital.sofianesadi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import test.andigital.sofianesadi.daos.INumberRepository;
import test.andigital.sofianesadi.entities.NumberEntity;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final INumberRepository kataNumberRepositoryImpl;

    @Autowired
    public CustomerController(INumberRepository kataNumberRepositoryImpl) {
        this.kataNumberRepositoryImpl = kataNumberRepositoryImpl;
    }

    /**
     * Get all phone number for the given customer
     *
     * @param id THe customer id
     * @return List of found number + 200 if found. 404 otherwise.
     */
    @GetMapping("{id}/numbers")
    public Flux<NumberEntity> getNumbers(@PathVariable(value = "id") String id) {
        return kataNumberRepositoryImpl.findByCustomerId(id);
    }
}
