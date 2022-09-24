package io.pact.example.junit;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import io.pact.example.junit.dogs.Dog;
import io.pact.example.junit.dogs.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("Demo backend")
@PactBroker
public class PactVerificationTest {
    @LocalServerPort
    private int port;

    @MockBean
    private DogRepository dogRepository;

    @BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State(value = "i have a list of dogs")
    void dogsExists() {
        Dog max = new Dog(1L, "Max", 3);
        when(dogRepository.findAll()).thenReturn(Arrays.asList(
                max,
                new Dog(200L, "Lassie", 8)
        ));
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(max));
        when(dogRepository.save(any())).thenReturn(Optional.of(max));
    }

    @State(value = "i can save a dog")
    void canSaveADog() {
        Dog max = new Dog(1L, "Max", 3);
        when(dogRepository.save(any())).thenReturn(max);
    }
}
