package sc.tyro.bundle.html5.input

import sc.tyro.core.component.Radio
import sc.tyro.web.CssIdentifier

import static sc.tyro.bundle.html5.input.Label.findFor

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=radio]')
class InputTypeRadio extends Radio {
    @Override
    boolean checked() {
        provider.check(id(), "it.is(':checked')")
    }

    @Override
    String label() {
        findFor(this)
    }
}
