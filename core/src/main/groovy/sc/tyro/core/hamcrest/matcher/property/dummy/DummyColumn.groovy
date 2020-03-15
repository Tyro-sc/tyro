package sc.tyro.core.hamcrest.matcher.property.dummy

import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.component.datagrid.Column

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DummyColumn extends Column {
    String title

    DummyColumn(String title) {
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
