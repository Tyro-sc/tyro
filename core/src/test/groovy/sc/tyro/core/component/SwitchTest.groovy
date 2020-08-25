package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.Switchable
import sc.tyro.core.support.UnSwitchable
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.state.SwitchSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Switch Component Tests")
class SwitchTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Switch in Component
        assert Switch in LabelSupport
        assert Switch in SwitchSupport
        assert Switch in Switchable
        assert Switch in UnSwitchable
    }
}
