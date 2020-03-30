package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("ListView Component Tests")
class ListViewTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert ListView in Component
        assert ListView in ItemSupport
        assert ListView in EmptySupport
    }
}