package sc.tyro.core.component.datagrid

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component
import sc.tyro.core.support.property.CellSupport
import sc.tyro.core.support.property.TitleSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Column Component Tests")
class ColumnTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Column in Component
        assert Column in TitleSupport
        assert Column in CellSupport
    }

    @Test
    @DisplayName("Should have identity based on Title")
    void identity() {
        Column column_1 = new TestColumn('title_1')
        Column column_2 = new TestColumn('title_2')
        Column column_3 = new TestColumn('title_1')

        assert column_1 != column_2
        assert column_1 == column_3

        assert column_1.hashCode() == 'title_1'.hashCode()
    }

    private class TestColumn extends Column {
        private String title

        TestColumn(String title) {
            this.title = title
        }

        List<Cell> cells() {
            return null
        }

        Cell cell(Object value) {
            return null
        }

        String title() {
            return title
        }
    }
}