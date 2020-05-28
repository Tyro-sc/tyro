package sc.tyro.core.input

import static sc.tyro.core.Config.provider

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Keyboard {
    static void type(Collection<?> keys) { provider.type(keys) }
}
