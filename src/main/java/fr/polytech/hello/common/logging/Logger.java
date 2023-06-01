package fr.polytech.hello.common.logging;

/**
 * General-purpose logger.
 */
public interface Logger {

    /**
     * Log a formatted message.
     *
     * @param format    message format
     * @param args      message arguments
     */
    void log(String format, Object... args);

    static Logger getLogger(String name) {
        return new ConsoleLogger(name);
    }
}
