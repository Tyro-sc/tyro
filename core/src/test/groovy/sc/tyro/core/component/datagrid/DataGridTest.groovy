package sc.tyro.core.component.datagrid

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component
import sc.tyro.core.support.property.ColumnSupport
import sc.tyro.core.support.property.RowSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("DataGrid Component Tests")
class DataGridTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert DataGrid in Component
        assert DataGrid in ColumnSupport
        assert DataGrid in RowSupport
    }
}