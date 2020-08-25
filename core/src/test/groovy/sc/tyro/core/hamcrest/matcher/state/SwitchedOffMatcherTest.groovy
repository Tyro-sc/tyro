package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.SwitchSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.off

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Switched State Matcher")
class SwitchedOffMatcherTest {
    @Test
    @DisplayName("Should support matcher Switched Off")
    void matcher() {
        SwitchSupport cmp = mock(SwitchSupport)

        when(cmp.on()).thenReturn(false)
        assertThat(cmp, is(off()))

        when(cmp.on()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(off())) })
        MatcherAssert.assertThat(error.message, is('\nExpected: is off\n     but: is on'))
    }
}