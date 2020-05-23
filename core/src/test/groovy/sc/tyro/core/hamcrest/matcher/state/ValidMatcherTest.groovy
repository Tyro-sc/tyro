package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.ValiditySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.valid

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Valid State Matcher")
class ValidMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ValiditySupport cmp = mock(ValiditySupport)

        // Valid
        when(cmp.valid()).thenReturn(true)
        assertThat(cmp, is(valid()))

        when(cmp.valid()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(valid())) })
        assertThat(error.message, is('\nExpected: is valid\n     but: is invalid'))
    }
}
