package sc.tyro.core

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Window {
    public final String id
    private final Provider provider

    Window(String id, Provider provider) {
        this.id = id
        this.provider = provider
    }

    void close() { provider.closeWindow(id) }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Window window = (Window) o
        id == window.id
    }

    int hashCode() { id.hashCode() }
}