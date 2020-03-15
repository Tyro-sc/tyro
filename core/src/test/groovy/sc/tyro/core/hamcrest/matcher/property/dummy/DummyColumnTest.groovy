package sc.tyro.core.hamcrest.matcher.property.dummy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

@DisplayName("Test Dummy Column")
class DummyColumnTest {
    @Test
    @DisplayName("Should store title")
    void column_title() {
        DummyColumn column = new DummyColumn('Column Title')

        assertThat(column.title(), is('Column Title'))
    }

    @Test
    @DisplayName("Should store other properties with default values")
    void column_default() {
        DummyColumn column = new DummyColumn('Column Title')

        assertThat(column.cells(), is(empty()))
        assertThat(column.cell(), is(nullValue()))
    }
}
