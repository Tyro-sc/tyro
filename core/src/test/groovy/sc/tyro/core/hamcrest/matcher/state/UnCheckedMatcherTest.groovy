package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.CheckSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.unchecked

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Unchecked State Matcher")
class UnCheckedMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        CheckSupport cmp = mock(CheckSupport)

        when(cmp.checked()).thenReturn(false)
        assertThat(cmp, is(unchecked()))

        when(cmp.checked()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(unchecked())) })
        assertThat(error.message, is('\nExpected: is unchecked\n     but: is checked'))
    }
}
