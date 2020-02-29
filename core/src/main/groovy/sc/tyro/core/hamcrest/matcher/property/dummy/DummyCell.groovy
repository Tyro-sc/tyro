package sc.tyro.core.hamcrest.matcher.property.dummy

import sc.tyro.core.component.datagrid.Cell

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DummyCell extends Cell {
    String value

    DummyCell(String value) {
        this.value = value
    }

    @Override
    Object value() {
        return value
    }
}
