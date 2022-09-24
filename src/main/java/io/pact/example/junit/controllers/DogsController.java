package io.pact.example.junit.controllers;

import io.pact.example.junit.dogs.Dog;
import io.pact.example.junit.dogs.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class DogsController {
  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "dog not found")
  public static class DogNotFoundException extends RuntimeException { }

  @Autowired
  private DogRepository dogRepository;

  @GetMapping("/dogs")
  public Iterable<Dog> allDogs() {
     return dogRepository.findAll();
  }

  @PostMapping(value = "/dogs", consumes = "application/json")
  public ResponseEntity save(@RequestBody Dog dog) {
    Dog savedDog = dogRepository.save(dog);
    return ResponseEntity.created(URI.create("/dogs/" + savedDog.getId())).build();
  }

  @GetMapping(value = "/dogs/{id}", produces = "application/json")
  public Dog dogById(@PathVariable("id") Long id) {
    return dogRepository.findById(id).orElseThrow(DogNotFoundException::new);
  }
}
