package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.ValiditySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.invalid

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Invalid State Matcher")
class InvalidMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ValiditySupport cmp = mock(ValiditySupport)

        when(cmp.valid()).thenReturn(false)
        assertThat(cmp, is(invalid()))

        when(cmp.valid()).thenReturn(true)
        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, is(invalid()))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: is invalid\n     but: is valid'))
    }
}
