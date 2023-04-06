package fr.polytech.hello.controller;

import fr.polytech.hello.controller.greeting.GreetingController;
import fr.polytech.hello.controller.homepage.HomePageController;
import jakarta.ws.rs.core.Application;
import java.util.Set;

public class WebApplication extends Application {

    public WebApplication() {
        System.out.println("WebApplication starts");
    }

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                GreetingController.class,
                HomePageController.class
        );
    }
}
