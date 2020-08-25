package sc.tyro.core.component

import sc.tyro.core.support.Switchable
import sc.tyro.core.support.UnSwitchable
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.state.SwitchSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Switch extends Component implements LabelSupport, SwitchSupport, Switchable, UnSwitchable {}