package fr.polytech.hello.model.user.encrypt;

/**
 * Password encrypter functionality.
 */
public interface PasswordEncrypter {

    /**
     * Encrypt input.
     *
     * @param input clear text input
     * @return      encrypted input
     */
    String encrypt(String input);
}
