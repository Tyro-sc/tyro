package sc.tyro.core

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ComponentException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     * @param message the message that will be displayed in the exception
     */
    ComponentException(String message) {
        super(message)
    }
}
