package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.Resettable
import sc.tyro.core.support.Submissible
import sc.tyro.core.support.state.ValiditySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Form Component Tests")
class FormTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Form in Component
        assert Form in ValiditySupport
        assert Form in Resettable
        assert Form in Submissible
    }
}