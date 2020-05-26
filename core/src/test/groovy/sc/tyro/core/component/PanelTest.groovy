package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.TitleSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Panel Component Tests")
class PanelTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Panel in Component
        assert Panel in TitleSupport
    }
}