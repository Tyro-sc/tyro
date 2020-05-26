package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.missing

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Missing State Matcher")
class MissingMatcherTest {
    @Test
    @DisplayName("Should support matcher Missing")
    void matcher() {
        Component cmp = mock(Component)

        when(cmp.available()).thenReturn(false)
        assertThat(cmp, is(missing()))

        when(cmp.available()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(missing())) })
        assertThat(error.message, is('\nExpected: is missing\n     but: is available'))
    }
}
