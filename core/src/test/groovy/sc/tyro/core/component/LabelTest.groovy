package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.TextSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Label Component Tests")
class LabelTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert Label in Component
        assert Label in TextSupport
    }
}
