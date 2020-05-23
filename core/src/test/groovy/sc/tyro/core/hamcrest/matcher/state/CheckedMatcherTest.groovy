package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.CheckSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.checked

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Checked State Matcher")
class CheckedMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        CheckSupport cmp = mock(CheckSupport)

        when(cmp.checked()).thenReturn(true)
        assertThat(cmp, is(checked()))

        when(cmp.checked()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(checked())) })
        assertThat(error.message, is('\nExpected: is checked\n     but: is unchecked'))
    }
}
