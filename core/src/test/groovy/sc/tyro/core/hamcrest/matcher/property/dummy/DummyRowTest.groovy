package sc.tyro.core.hamcrest.matcher.property.dummy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

@DisplayName("Test Dummy Row")
class DummyRowTest {
    @Test
    @DisplayName("Should store title")
    void row_title() {
        DummyRow row = new DummyRow('Row Title')

        assertThat(row.title(), is('Row Title'))
    }

    @Test
    @DisplayName("Should store other properties with default values")
    void row_default() {
        DummyRow row = new DummyRow('Row Title')

        assertThat(row.cells(), is(empty()))
        assertThat(row.cell(), is(nullValue()))
    }
}
