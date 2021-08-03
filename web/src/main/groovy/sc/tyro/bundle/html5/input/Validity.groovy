package sc.tyro.bundle.html5.input

/**
 * @author David Avenante
 * @since 1.0.0
 */
trait Validity {
    boolean valid() {
        provider.check(id(), "it[0].validity.valid")
    }

    String validationMessage() {
        provider.eval(id(), "it[0].validationMessage")
    }
}