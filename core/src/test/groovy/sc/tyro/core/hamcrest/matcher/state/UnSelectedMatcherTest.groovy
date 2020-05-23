package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.SelectSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.unselected

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Unselected State Matcher")
class UnSelectedMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        SelectSupport cmp = mock(SelectSupport)

        when(cmp.selected()).thenReturn(false)
        assertThat(cmp, is(unselected()))

        when(cmp.selected()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(unselected())) })
        assertThat(error.message, is('\nExpected: is unselected\n     but: is selected'))
    }
}
