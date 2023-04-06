package fr.polytech.hello.model.greeting;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public Greeting greet(String name) {
        return new Greeting("Hello %s".formatted(name));
    }
}
