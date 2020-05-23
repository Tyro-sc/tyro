package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.EmptySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.filled

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Filled State Matcher")
class FilledMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        EmptySupport cmp = mock(EmptySupport)

        when(cmp.empty()).thenReturn(false)
        assertThat(cmp, is(filled()))

        when(cmp.empty()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(filled())) })
        assertThat(error.message, is('\nExpected: is filled\n     but: is empty'))
    }
}
