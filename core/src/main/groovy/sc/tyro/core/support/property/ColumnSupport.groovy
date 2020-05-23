package sc.tyro.core.support.property

import sc.tyro.core.component.datagrid.Column

/**
 * @author David Avenante
 * @since 1.0.0
 */
public interface ColumnSupport {
    public List<Column> columns()

    public Column column(String title)
}
