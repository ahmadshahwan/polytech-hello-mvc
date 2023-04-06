package fr.polytech.hello.model.user.encrypt;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * MD5-based password encrypter implementation.
 */
@ApplicationScoped
public class MD5PasswordEncrypter extends BasePasswordEncrypter {
    @Override
    protected String getAlgorithm() {
        return "MD5";
    }
}
