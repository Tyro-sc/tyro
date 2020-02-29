package sc.tyro.core.input

import static sc.tyro.core.Config.provider

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Keyboard {
    public void type(Collection<?> keys) { provider.type(keys) }
}
