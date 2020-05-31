package sc.tyro.bundle.html5.input


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
        // provider.eval(id(), "it.val(" + value + ")") fail on Firefox
        // Use standard DOM access cause FF issue
        provider.eval(null, "document.getElementById('${id()}').value = '${value}'")
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
