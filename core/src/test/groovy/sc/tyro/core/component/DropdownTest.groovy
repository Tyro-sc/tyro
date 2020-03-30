package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.Selectable
import sc.tyro.core.support.property.GroupSupport
import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.property.SelectedItemSupport
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Dropdown Component Tests")
class DropdownTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert Dropdown in Component
        assert Dropdown in ItemSupport
        assert Dropdown in GroupSupport
        assert Dropdown in LabelSupport
        assert Dropdown in Selectable
        assert Dropdown in SelectedItemSupport
        assert Dropdown in EmptySupport
    }
}