package io.pact.example.junit.dogs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dog {
  @Id
  @GeneratedValue
  @JsonIgnore
  private Long id;
  private String name;
  private int age;

  public Dog(Long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Dog() {
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return age;
  }
}
