package fr.polytech.hello.model.user;

public record User(
        String name,
        String password
) {
    public User(String name) {
        this(name, "");
    }
}
