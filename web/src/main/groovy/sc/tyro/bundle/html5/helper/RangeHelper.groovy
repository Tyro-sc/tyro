package sc.tyro.bundle.html5.helper

import sc.tyro.core.Config
import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
class RangeHelper {
    static Object minimum(Component c) {
        Config.provider.eval(c.id(), "it.prop('min')")
    }

    static Object maximum(Component c) {
        Config.provider.eval(c.id(), "it.prop('max')")
    }

    static Number step(Component c) {
        Object value = Config.provider.eval(c.id(), "it.prop('step')")
        if (value)
            value as BigDecimal
        else
            0
    }

    static boolean inRange(Component c) {
        !(Config.provider.check(c.id(), "it[0].validity.rangeUnderflow") ||
                Config.provider.check(c.id(), "it[0].validity.rangeOverflow"))
    }
}
