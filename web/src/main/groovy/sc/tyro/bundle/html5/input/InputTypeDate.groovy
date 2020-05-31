package sc.tyro.bundle.html5.input

import sc.tyro.core.component.field.DateField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=date]')
class InputTypeDate extends DateField implements Input {
    @Override
    void value(Object value) {
        provider.runScript("\$('[id=\"${id()}\"]').val('" + value + "')")
    }

    @Override
    Object minimum() {
        provider.eval(id(), "it.prop('min')")
    }

    @Override
    Object maximum() {
        provider.eval(id(), "it.prop('max')")
    }

    @Override
    Object step() {
        Object value = provider.eval(id(), "it.prop('step')")
        return  (value) ? value as BigDecimal : 0
    }

    @Override
    boolean inRange() {
        !(provider.check(id(), "it[0].validity.rangeUnderflow") || provider.check(id(), "it[0].validity.rangeOverflow"))
    }
}
