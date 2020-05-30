package sc.tyro.core.support.property

import sc.tyro.core.component.datagrid.Row

/**
 * @author David Avenante
 * @since 1.0.0
 */
interface RowSupport {
    List<Row> rows()

    Row row(String title)
}
