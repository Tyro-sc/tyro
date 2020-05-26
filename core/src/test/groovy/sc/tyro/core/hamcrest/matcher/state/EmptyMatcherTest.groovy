package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.EmptySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.empty

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Empty State Matcher")
class EmptyMatcherTest {
    @Test
    @DisplayName("Should support matcher Empty")
    void matcher() {
        EmptySupport cmp = mock(EmptySupport)

        when(cmp.empty()).thenReturn(true)
        assertThat(cmp, is(empty()))

        when(cmp.empty()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(empty())) })
        assertThat(error.message, is('\nExpected: is empty\n     but: is filled'))
    }
}
