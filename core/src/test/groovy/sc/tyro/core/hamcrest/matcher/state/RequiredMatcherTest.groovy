package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.RequiredSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.required

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Required State Matcher")
class RequiredMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        RequiredSupport cmp = mock(RequiredSupport)

        when(cmp.required()).thenReturn(true)
        assertThat(cmp, is(required()))

        when(cmp.required()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(required())) })
        assertThat(error.message, is('\nExpected: is required\n     but: is optional'))
    }
}
