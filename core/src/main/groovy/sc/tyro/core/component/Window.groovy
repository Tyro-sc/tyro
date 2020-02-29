package sc.tyro.core.component

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Window {
//    private final Provider provider
//    final String id
//
//    Window(Provider provider, String id) {
//        this.provider = provider
//        this.id = id
//    }
//
//    void close() { provider.closeWindow(this.id) }

    @Override
    String toString() { this.id }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Window window = (Window) o
        id == window.id
    }

    int hashCode() { id.hashCode() }
}