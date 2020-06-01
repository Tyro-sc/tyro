package sc.tyro.core.component.field

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.InputSupport
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.property.ValueSupport
import sc.tyro.core.support.state.FocusSupport
import sc.tyro.core.support.state.ValiditySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Field extends Component implements LabelSupport, InputSupport,
        ValueSupport, ValiditySupport, FocusSupport {}