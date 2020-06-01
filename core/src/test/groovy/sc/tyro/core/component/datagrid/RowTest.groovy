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
@DisplayName("DataGrid Component Tests")
class RowTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Row in Component
        assert Row in TitleSupport
        assert Row in CellSupport
    }

    @Test
    @DisplayName("Should have identity based on Title")
    void identity() {
        Row row_1 = new TestRow('title_1')
        Row row_2 = new TestRow('title_2')
        Row row_3 = new TestRow('title_1')

        assert row_1 != row_2
        assert row_1 == row_3

        assert row_1.hashCode() == 'title_1'.hashCode()
    }

    private class TestRow extends Row {
        private String title

        TestRow(String title) {
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