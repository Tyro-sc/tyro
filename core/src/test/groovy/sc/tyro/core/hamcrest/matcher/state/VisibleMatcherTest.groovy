package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.visible

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Visible State Matcher")
class VisibleMatcherTest {
    @Test
    @DisplayName("Should support matcher Visible")
    void matcher() {
        Component cmp = mock(Component)

        when(cmp.visible()).thenReturn(true)
        assertThat(cmp, is(visible()))

        when(cmp.visible()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(visible())) })
        assertThat(error.message, is('\nExpected: is visible\n     but: is hidden'))
    }
}
