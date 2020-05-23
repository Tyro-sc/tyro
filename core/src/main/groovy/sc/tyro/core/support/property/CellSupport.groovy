package sc.tyro.core.support.property

import sc.tyro.core.component.datagrid.Cell

/**
 * @author David Avenante
 * @since 1.0.0
 */
public interface CellSupport {
    public List<Cell> cells()

    public Cell cell(Object value)
}
