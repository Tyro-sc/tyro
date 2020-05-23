package sc.tyro.core

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Window {
    public final String id
    private final Provider provider

    public Window(String id, Provider provider) {
        this.id = id
        this.provider = provider
    }

    public void close() { provider.closeWindow(id) }

    public boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Window window = (Window) o
        id == window.id
    }

    public int hashCode() { id.hashCode() }
}