package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.ReadOnlySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.readOnly

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Read Only State Matcher")
class ReadOnlyMatcherTest {
    @Test
    @DisplayName("Should support matcher ReadOnly")
    void matcher() {
        ReadOnlySupport cmp = mock(ReadOnlySupport)

        when(cmp.readOnly()).thenReturn(true)
        assertThat(cmp, is(readOnly()))

        when(cmp.readOnly()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(readOnly())) })
        assertThat(error.message, is('\nExpected: is read only\n     but: is not read only'))
    }
}
