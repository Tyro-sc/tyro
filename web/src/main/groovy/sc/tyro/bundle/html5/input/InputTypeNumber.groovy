package sc.tyro.bundle.html5.input

import sc.tyro.core.ComponentException
import sc.tyro.core.component.field.NumberField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=number]')
class InputTypeNumber extends NumberField implements Input {
    Number value() {
        Object value = org.testatoo.core.Testatoo.getConfig.evaluator.eval(id(), "it.val()")
        if (value)
            value as BigDecimal
        else
            throw new ComponentException("${this.class.simpleName} ${this} is empty and has no value")
    }

    @Override
    Number minimum() {
        RangeHelper.minimun(this) as BigDecimal
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
