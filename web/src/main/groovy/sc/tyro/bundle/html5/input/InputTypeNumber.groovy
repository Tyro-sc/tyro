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
        Object value = provider.eval(id(), "it.val()")
        if (value)
            value as BigDecimal
        else
            throw new ComponentException("${this.class.simpleName} ${this} is empty and has no value")
    }

    @Override
    Number minimum() {
        provider.eval(id(), "it.prop('min')") as BigDecimal
    }

    @Override
    Number maximum() {
        provider.eval(id(), "it.prop('max')") as BigDecimal
    }

    @Override
    Number step() {
        Object value = provider.eval(id(), "it.prop('step')")
        return  (value) ? value as BigDecimal : 0
    }

    @Override
    boolean inRange() {
        !(provider.check(id(), "it[0].validity.rangeUnderflow") || provider.check(id(), "it[0].validity.rangeOverflow"))
    }
}
