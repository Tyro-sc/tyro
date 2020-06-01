package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.Checkable
import sc.tyro.core.support.UnCheckable
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.state.CheckSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Checkbox Component Tests")
class CheckBoxTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert CheckBox in Component
        assert CheckBox in CheckSupport
        assert CheckBox in LabelSupport
        assert CheckBox in Checkable
        assert CheckBox in UnCheckable
    }
}