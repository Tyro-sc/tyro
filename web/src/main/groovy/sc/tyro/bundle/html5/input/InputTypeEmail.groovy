package sc.tyro.bundle.html5.input

import sc.tyro.core.component.field.EmailField
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=email]')
class InputTypeEmail extends EmailField implements Input {}
