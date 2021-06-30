package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.field.Field
import sc.tyro.core.support.Selectable

import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.property.SelectedItemSupport
import sc.tyro.core.support.state.EmptySupport

/**
 * @author Ludovic Avenante
 * @since 1.0.0
 */
@DisplayName("Combobox Component Tests")
class ComboboxTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Combobox in Field
        assert Combobox in ItemSupport
        assert Combobox in LabelSupport
        assert Combobox in Selectable
        assert Combobox in SelectedItemSupport
        assert Combobox in EmptySupport
    }
}