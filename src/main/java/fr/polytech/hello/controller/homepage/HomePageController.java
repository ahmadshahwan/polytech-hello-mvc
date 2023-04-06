package fr.polytech.hello.controller.homepage;

import fr.polytech.hello.common.logging.Logger;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import java.io.InputStream;

@Path("/")
public class HomePageController {

    private final Logger logger;

    @Inject
    public HomePageController(
        Logger logger
    ) {
        this.logger = logger;
    }

    @GET
    public InputStream getIndex() {
        this.logger.log("Visiting home page.");
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream("view/index.html");
        if (inputStream == null) {
            throw new NotFoundException("index.html not found");
        }
        return inputStream;
    }
}
