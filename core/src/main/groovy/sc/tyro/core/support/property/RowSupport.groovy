package sc.tyro.core.support.property

import sc.tyro.core.component.datagrid.Row

/**
 * @author David Avenante
 * @since 1.0.0
 */
public interface RowSupport {
    public List<Row> rows()

    public Row row(String title)
}
