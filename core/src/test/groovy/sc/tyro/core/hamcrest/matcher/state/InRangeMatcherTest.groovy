package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.RangeSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.inRange

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("In Range State Matcher")
class InRangeMatcherTest {
    @Test
    @DisplayName("Should support matcher InRange")
    void matcher() {
        RangeSupport cmp = mock(RangeSupport)

        when(cmp.inRange()).thenReturn(true)
        assertThat(cmp, is(inRange()))

        when(cmp.inRange()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(inRange())) })
        assertThat(error.message, is('\nExpected: is in range\n     but: is out of range'))
    }
}
