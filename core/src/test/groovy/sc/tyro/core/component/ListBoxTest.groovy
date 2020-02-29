package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.Selectable
import sc.tyro.core.support.UnSelectable
import sc.tyro.core.support.property.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("ListBox Component Tests")
class ListBoxTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert ListBox in Component
        assert ListBox in ItemSupport
        assert ListBox in GroupSupport
        assert ListBox in SelectedItemsSupport
        assert ListBox in VisibleItemsSupport
        assert ListBox in LabelSupport
        assert ListBox in Selectable
        assert ListBox in UnSelectable
    }
}
