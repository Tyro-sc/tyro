package sc.tyro.core.component.datagrid

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component
import sc.tyro.core.support.property.ValueSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Cell Component Tests")
class CellTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Cell in Component
        assert Cell in ValueSupport
    }

    @Test
    @DisplayName("Should have identity based on Value")
    void identity() {
        Cell cell_1 = new TestCell('value_1')
        Cell cell_2 = new TestCell('value_2')
        Cell cell_3 = new TestCell('value_1')

        assert cell_1 != cell_2
        assert cell_1 == cell_3

        assert cell_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestCell extends Cell {
        private String value

        TestCell(String value) {
            this.value = value
        }

        Object value() {
            return value
        }
    }
}