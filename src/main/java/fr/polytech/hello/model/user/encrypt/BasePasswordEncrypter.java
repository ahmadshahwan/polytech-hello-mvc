package fr.polytech.hello.model.user.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Password encrypter abstract implementation.
 */
public abstract class BasePasswordEncrypter implements PasswordEncrypter {

    @Override
    public String encrypt(String input) {
        try {
            String algorithm = this.getAlgorithm();
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes());
            byte[] digest = md.digest();
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Name of the algorithm used for encryption. The name should follow Java
     * Security Standard Algorithm Names Specification.
     *
     * @return  algorithm name
     */
    protected abstract String getAlgorithm();
}
