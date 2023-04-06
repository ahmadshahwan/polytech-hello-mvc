package fr.polytech.hello.model.user;

public interface UserService {

    void create(String username, String password);

    void update(String username, String password);
}
