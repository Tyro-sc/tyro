package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.SwitchSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.on

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Switched State Matcher")
class SwitchedOnMatcherTest {
    @Test
    @DisplayName("Should support matcher Switched On")
    void matcher() {
        SwitchSupport cmp = mock(SwitchSupport)

        when(cmp.on()).thenReturn(true)
        assertThat(cmp, is(on()))

        when(cmp.on()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(on())) })
        assertThat(error.message, is('\nExpected: is on\n     but: is off'))
    }
}