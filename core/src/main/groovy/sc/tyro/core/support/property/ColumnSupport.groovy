package sc.tyro.core.support.property

import sc.tyro.core.component.datagrid.Column

/**
 * @author David Avenante
 * @since 1.0.0
 */
interface ColumnSupport {
    List<Column> columns()

    Column column(String title)
}
