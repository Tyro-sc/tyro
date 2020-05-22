package sc.tyro.bundle.html5.input

import sc.tyro.core.component.field.DateTimeField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=datetime]')
class InputTypeDateTime extends DateTimeField implements Input {
    @Override
    void value(Object value) {
        provider.runScript("\$('[id=\"${id()}\"]').val('" + value + "')")
    }
}
