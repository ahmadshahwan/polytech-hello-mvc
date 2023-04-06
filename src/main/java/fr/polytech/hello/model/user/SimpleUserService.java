package fr.polytech.hello.model.user;

import fr.polytech.hello.model.user.encrypt.PasswordEncrypter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimpleUserService implements UserService {

    private final UserRepository repository;
    private final PasswordEncrypter encrypter;

    @Inject
    public SimpleUserService(
           UserRepository repository,
           PasswordEncrypter encrypter
    ) {
        this.repository = repository;
        this.encrypter = encrypter;
    }

    @Override
    public void create(String username, String password) {
        User user = new User(username, this.encrypter.encrypt(password));
        this.repository.create(user);
    }

    @Override
    public void update(String username, String password) {
        User user = new User(username, this.encrypter.encrypt(password));
        this.repository.update(user);
    }
}
