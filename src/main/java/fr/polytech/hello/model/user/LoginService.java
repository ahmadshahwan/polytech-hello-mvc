package fr.polytech.hello.model.user;

public interface LoginService {

    User getConnectedUser();

    boolean login(String username, String password);

    void logout();
}
