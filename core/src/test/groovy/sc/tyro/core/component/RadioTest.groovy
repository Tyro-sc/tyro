package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.Checkable
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.state.CheckSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Radio Component Tests")
class RadioTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Radio in Component
        assert Radio in LabelSupport
        assert Radio in CheckSupport
        assert Radio in Checkable
    }
}