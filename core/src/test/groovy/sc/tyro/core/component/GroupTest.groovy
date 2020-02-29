package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Group Component Tests")
class GroupTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert Group in Component
    }

    @Test
    @DisplayName("Should have identity based on Value")
    void should_have_equality_and_hashcode_based_on_value() {
        Group group_1 = new TestGroup('value_1')
        Group group_2 = new TestGroup('value_2')
        Group group_3 = new TestGroup('value_1')

        assert !group_1.equals(group_2)
        assert group_1.equals(group_3)

        assert group_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestGroup extends Group {
        private String value;

        TestGroup(String value) {
            this.value = value
        }

        List<Item> items() {
            return null
        }

        Item item(String value) {
            return null
        }

        Object value() {
            return value
        }
    }
}