package io.pact.example.junit.controllers;

import io.pact.example.junit.dogs.Dog;
import io.pact.example.junit.dogs.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogsController {
  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "dog not found")
  public static class DogNotFoundException extends RuntimeException { }

  @Autowired
  private DogRepository dogRepository;

  @GetMapping(value = "/dogs", produces = "application/json")
  public Iterable<Dog> allDogs() {
     return dogRepository.findAll();
  }

  @GetMapping(value = "/dogs/{id}", produces = "application/json")
  public Dog dogById(@PathVariable("id") Long id) {
    return dogRepository.findById(id).orElseThrow(DogNotFoundException::new);
  }
}
