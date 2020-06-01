package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ReferenceSupport
import sc.tyro.core.support.property.TextSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Link Component Tests")
class LinkTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Link in Component
        assert Link in TextSupport
        assert Link in ReferenceSupport
    }
}