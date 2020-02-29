package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ValueSupport
import sc.tyro.core.support.state.SelectSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Item Component Tests")
class ItemTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert Item in Component
        assert Item in SelectSupport
        assert Item in ValueSupport
    }

    @Test
    @DisplayName("Should have identity based on Value")
    void should_have_equality_and_hashcode_based_on_value() {
        Item item_1 = new TestItem('value_1')
        Item item_2 = new TestItem('value_2')
        Item item_3 = new TestItem('value_1')

        assert !item_1.equals(item_2)
        assert item_1.equals(item_3)

        assert item_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestItem extends Item {
        private String value

        TestItem(String value) {
            this.value = value
        }

        Object value() {
            return value
        }

        boolean selected() {
            return false
        }
    }
}