package fr.polytech.hello.model.user;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@ApplicationScoped
public class FileBasedUserRepository implements UserRepository {

    private static final String FILE_NAME = "passwords.properties";

    private final Map<String, String> passwords = new HashMap<>();

    @PostConstruct
    public void init() {
        Properties passwords = readFile();
        if (passwords != null) {
            passwords.forEach((key, value) ->
                    this.passwords.put(key.toString(), value.toString()));
        }
    }

    private Properties readFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return null;
        }
        try {
            InputStream inputStream = new FileInputStream(FILE_NAME);
            Properties passwords = new Properties();
            passwords.load(inputStream);
            return passwords;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Optional<User> retrieve(String name) {
        return this.passwords.entrySet().stream()
                .filter((Map.Entry<String, String> e) -> name.equals(e.getKey()))
                .map(e -> new User(e.getKey(), e.getValue()))
                .findAny();
    }

    @Override
    public List<User> retrieveAll() {
        return this.passwords.entrySet().stream()
                .map(e -> new User(e.getKey(), e.getValue()))
                .toList();
    }

    @Override
    public void update(User user) {
        this.passwords.keySet().stream()
                .filter(user.name()::equals)
                .findAny()
                .ifPresent(name -> this.passwords.put(name, user.password()));
        this.commit();
    }

    @Override
    public void create(User user) {
        this.passwords.put(user.name(), user.password());
        this.commit();
    }

    private synchronized void commit() {
        Properties passwords = new Properties();
        passwords.putAll(this.passwords);
        try {
            passwords.store(new FileWriter(FILE_NAME), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
