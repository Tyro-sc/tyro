package sc.tyro.core.component

import sc.tyro.core.support.property.ValueSupport
import sc.tyro.core.support.state.SelectSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public abstract class Item extends Component implements SelectSupport, ValueSupport {
    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (!o in Item) return false
        Item item = (Item) o
        value() == item.value()
    }

    @Override
    int hashCode() { value().hashCode() }
}