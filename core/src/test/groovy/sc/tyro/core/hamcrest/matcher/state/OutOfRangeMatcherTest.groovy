package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.RangeSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.outOfRange

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Out Of Range State Matcher")
class OutOfRangeMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        RangeSupport cmp = mock(RangeSupport)

        when(cmp.inRange()).thenReturn(false)
        assertThat(cmp, is(outOfRange()))

        when(cmp.inRange()).thenReturn(true)
        Error error = assertThrows(AssertionError, { assertThat(cmp, is(outOfRange())) })
        assertThat(error.message, is('\nExpected: is out of range\n     but: is in range'))
    }
}
