package sc.tyro.bundle.html5.input

import sc.tyro.core.component.field.SearchField
import sc.tyro.core.support.property.LengthSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('input[type=search]')
class InputTypeSearch extends SearchField implements Input, LengthSupport {}
