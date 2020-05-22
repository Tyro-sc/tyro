package sc.tyro.bundle.html5.input


import sc.tyro.core.component.field.PhoneField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=tel]')
class InputTypeTel extends PhoneField implements Input {
    String getPattern() {
        provider.eval(id(), "it.prop('pattern')")
    }
}
