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
        RangeHelper.minimun(this)
    }

    @Override
    Object maximum() {
        RangeHelper.maximum(this)
    }

    @Override
    Object step() {
        RangeHelper.step(this)
    }

    @Override
    boolean inRange() {
        RangeHelper.inRange(this)
    }
}
