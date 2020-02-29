package sc.tyro.core.component

import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.property.ValueSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public abstract class Group extends Component implements ValueSupport, ItemSupport {
    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (!o in Group) return false
        Group group = (Group) o
        value() == group.value()
    }

    @Override
    int hashCode() { value().hashCode() }
}