package sc.tyro.core.hamcrest.matcher.property.dummy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

@DisplayName("Test Dummy Cell")
class DummyCellTest {
    @Test
    @DisplayName("Should store value")
    void cell_value() {
        DummyCell cell = new DummyCell('Cell Value')

        assertThat(cell.value(), is('Cell Value'))
    }
}
