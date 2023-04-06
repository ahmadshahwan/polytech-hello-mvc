package fr.polytech.hello.model.user;

import fr.polytech.hello.model.user.encrypt.PasswordEncrypter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimpleLoginService implements LoginService {

    private final UserRepository repository;
    private final PasswordEncrypter encrypter;
    private User connectedUser;

    @Inject
    public SimpleLoginService(
            UserRepository repository,
            PasswordEncrypter encrypter
    ) {
        this.repository = repository;
        this.encrypter = encrypter;
    }

    @Override
    public User getConnectedUser() {
        return this.connectedUser;
    }

    @Override
    public boolean login(String username, String password) {
        User user = this.repository.retrieve(username).orElse(null);
        if (user == null ||
                !this.encrypter.encrypt(password).equals(user.password())) {
            return false;
        }
        this.connectedUser = user;
        return true;
    }

    @Override
    public void logout() {
        this.connectedUser = null;
    }
}
