package sc.tyro.core.component

import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class ListView extends Component implements ItemSupport, EmptySupport {
    abstract Item item(String value)
}