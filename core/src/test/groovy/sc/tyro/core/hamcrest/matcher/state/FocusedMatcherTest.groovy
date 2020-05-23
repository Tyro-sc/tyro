package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.FocusSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.focused

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Focused State Matcher")
class FocusedMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        FocusSupport cmp = mock(FocusSupport)

        when(cmp.focused()).thenReturn(true)
        assertThat(cmp, is(focused()))

        when(cmp.focused()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(focused())) })
        assertThat(error.message, is('\nExpected: is focused\n     but: is not focused'))
    }
}
