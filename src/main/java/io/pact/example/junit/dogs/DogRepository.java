package io.pact.example.junit.dogs;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DogRepository extends CrudRepository<Dog, Long> {

    Optional<Dog> findByName(String name);
}
