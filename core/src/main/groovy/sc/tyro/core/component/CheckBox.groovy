package sc.tyro.core.component

import sc.tyro.core.support.Checkable
import sc.tyro.core.support.UnCheckable
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.state.CheckSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public abstract class CheckBox extends Component implements CheckSupport, LabelSupport, Checkable, UnCheckable {}