package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.TextSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Button Component Tests")
class ButtonTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Button in Component
        assert Button in TextSupport
    }
}