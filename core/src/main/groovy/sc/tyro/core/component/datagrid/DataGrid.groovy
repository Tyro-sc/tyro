package sc.tyro.core.component.datagrid

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.ColumnSupport
import sc.tyro.core.support.property.RowSupport
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public abstract class DataGrid extends Component
        implements ColumnSupport, RowSupport, EmptySupport {}