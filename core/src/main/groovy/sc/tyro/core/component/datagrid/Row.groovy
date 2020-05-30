package sc.tyro.core.component.datagrid

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.CellSupport
import sc.tyro.core.support.property.TitleSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Row extends Component implements TitleSupport, CellSupport {
    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (!o in Row) return false
        Row row = (Row) o
        title() == row.title()
    }

    @Override
    int hashCode() { title().hashCode() }
}