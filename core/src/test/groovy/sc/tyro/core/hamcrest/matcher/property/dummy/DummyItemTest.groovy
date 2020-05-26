package sc.tyro.core.hamcrest.matcher.property.dummy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

@DisplayName("Test Dummy Item")
class DummyItemTest {
    @Test
    @DisplayName("Should store value")
    void value() {
        DummyItem item = new DummyItem('Item Value')

        assertThat(item.value(), is('Item Value'))
    }

    @Test
    @DisplayName("Should store other properties with default values")
    void defaultValue() {
        DummyItem item = new DummyItem('Item Value')

        assertThat(item.selected(), is(false))
    }
}
