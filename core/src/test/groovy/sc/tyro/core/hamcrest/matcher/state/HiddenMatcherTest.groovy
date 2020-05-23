package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.hidden

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Hidden State Matcher")
class HiddenMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.visible()).thenReturn(false)
        assertThat(cmp, is(hidden()))

        when(cmp.visible()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(hidden())) })
        assertThat(error.message, is('\nExpected: is hidden\n     but: is visible'))
    }
}
