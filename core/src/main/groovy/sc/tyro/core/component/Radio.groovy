package sc.tyro.core.component

import sc.tyro.core.support.Checkable
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.state.CheckSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Radio extends Component implements LabelSupport, CheckSupport, Checkable {}