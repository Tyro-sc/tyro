package sc.tyro.bundle.html5.input

import sc.tyro.core.component.field.TimeField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=time]')
class InputTypeTime extends TimeField implements Input {
    @Override
    void value(Object value) {
        provider.runScript("\$('[id=\"${id()}\"]').val('" + value + "')")
    }
}
