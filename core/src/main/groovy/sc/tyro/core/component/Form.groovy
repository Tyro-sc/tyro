package sc.tyro.core.component

import sc.tyro.core.support.Resettable
import sc.tyro.core.support.Submissible
import sc.tyro.core.support.state.ValiditySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Form extends Component implements ValiditySupport, Resettable, Submissible {
}