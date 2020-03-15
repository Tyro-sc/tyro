package sc.tyro.core.hamcrest.matcher.property.dummy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

@DisplayName("Test Dummy Group")
class DummyGroupTest {
    @Test
    @DisplayName("Should store value")
    void group_value() {
        DummyGroup group = new DummyGroup('Group Value')

        assertThat(group.value(), is('Group Value'))
    }

    @Test
    @DisplayName("Should store other properties with default values")
    void group_default() {
        DummyGroup group = new DummyGroup('Group Value')

        assertThat(group.items(), is(empty()))
        assertThat(group.item(""), is(nullValue()))
    }
}