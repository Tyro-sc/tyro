package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ReferenceSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Image Component Tests")
class ImageTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void should_have_expected_inheritance() {
        assert Image in Component
        assert Image in ReferenceSupport
    }
}
