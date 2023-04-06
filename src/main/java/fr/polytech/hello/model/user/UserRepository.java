package fr.polytech.hello.model.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> retrieve(String name);

    List<User> retrieveAll();

    void update(User user);

    void create(User user);
}
