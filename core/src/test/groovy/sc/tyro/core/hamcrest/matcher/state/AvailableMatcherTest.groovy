package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.available

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Available State Matcher")
class AvailableMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.available()).thenReturn(true)
        assertThat(cmp, is(available()))

        when(cmp.available()).thenReturn(false)

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, is(available()))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: is available\n     but: is missing'))
    }
}
