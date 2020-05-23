package sc.tyro.bundle.html5.input

import sc.tyro.bundle.html5.helper.RangeHelper
import sc.tyro.core.component.field.RangeField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=range]')
class InputTypeRange extends RangeField implements Input {
    Number value() {
        provider.eval(id(), "it.val()") as BigDecimal
    }

    void value(Object value) {
        provider.eval(id(), "it.val(" + value + ")")
    }

    @Override
    Number minimum() {
        RangeHelper.minimum(this) as BigDecimal
    }

    @Override
    Number maximum() {
        RangeHelper.maximum(this) as BigDecimal
    }

    @Override
    Number step() {
        RangeHelper.step(this)
    }

    @Override
    boolean inRange() {
        RangeHelper.inRange(this)
    }
}
