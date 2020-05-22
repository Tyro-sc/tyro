package sc.tyro.bundle.html5.input

import sc.tyro.bundle.html5.helper.LabelHelper
import sc.tyro.core.component.CheckBox
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=checkbox]')
class InputTypeCheckBox extends CheckBox {
    @Override
    boolean checked() {
        provider.check(id(), "it.is(':checked')")
    }

    @Override
    String label() {
        LabelHelper.label(this)
    }
}