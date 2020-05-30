package sc.tyro.core.component.datagrid

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.ValueSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Cell extends Component implements ValueSupport {
    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (!o in Cell) return false
        Cell cell = (Cell) o
        value() == cell.value()
    }

    @Override
    int hashCode() { value().hashCode() }
}