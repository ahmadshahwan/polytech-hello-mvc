package fr.polytech.hello.controller.greeting;

import fr.polytech.hello.common.logging.Logger;
import fr.polytech.hello.model.greeting.Greeting;
import fr.polytech.hello.model.greeting.GreetingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/greeting")
public class GreetingController {

    private final Logger logger;
    private final GreetingService greetingService;

    @Inject
    public GreetingController(
            GreetingService greetingService,
            Logger logger
    ) {
        this.logger = logger;
        this.greetingService = greetingService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting greet() {
        this.logger.log("Saying hello");
        String name = "John";
        return this.greetingService.greet(name);
    }
}
