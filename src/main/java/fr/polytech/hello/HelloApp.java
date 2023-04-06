package fr.polytech.hello;

import fr.polytech.hello.controller.WebApplication;
import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.core.Application;

/**
 * Hello App.
 */
public class HelloApp {

    private static final int PORT = 8080;

    private final Application webApp;
    private SeBootstrap.Instance instance;

    public HelloApp(WebApplication webApp) {
        this.webApp = webApp;
    }

    public void run() {
        try {
            SeBootstrap.Configuration configuration = SeBootstrap.Configuration
                    .builder()
                    .port(PORT)
                    .build();
            SeBootstrap.start(this.webApp, configuration)
                    .thenAccept(this::accept)
                    .handle(this::handle);
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (this.instance != null) {
                this.instance.stop();
            }
        }
    }

    public static void main(String[] args) {
        new HelloApp(new WebApplication()).run();
    }

    private <T> T handle(T instance, Throwable e) {
        e.printStackTrace();
        return instance;
    }

    private void accept(SeBootstrap.Instance instance) {
        this.instance = instance;
        System.out.printf(
                "Web app running on %s\n",
                instance.configuration().baseUri()
        );
    }

}
