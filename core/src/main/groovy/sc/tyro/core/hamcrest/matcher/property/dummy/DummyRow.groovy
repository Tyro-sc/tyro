package sc.tyro.core.hamcrest.matcher.property.dummy

import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.component.datagrid.Row

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DummyRow extends Row {
    String title

    DummyRow(String title) {
        this.title = title
    }

    @Override
    List<Cell> cells() {
        return []
    }

    @Override
    Cell cell(Object value) {
        return null
    }

    @Override
    String title() {
        return title
    }
}
