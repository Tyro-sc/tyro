package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.contain

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Contain State Matcher")
class ContainMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        Component container = mock(Component)
        Component cmp_1 = mock(Component)
        Component cmp_2 = mock(Component)

        when(container.toString()).thenReturn("Component:container")
        when(cmp_1.toString()).thenReturn("Component:1")
        when(cmp_2.toString()).thenReturn("Component:2")

        when(container.contains(cmp_1)).thenReturn(true)
        when(container.contains(cmp_2)).thenReturn(true)

        assertThat(container, contain(cmp_1, cmp_2))

        when(container.contains(cmp_2)).thenReturn(false)

        AssertionError error = assertThrows(AssertionError, {
            assertThat(container, contain(cmp_1, cmp_2))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: Component Component:container contains [Component:1, Component:2]\n     but: does not contains expected component(s): [Component:2]'))
    }
}