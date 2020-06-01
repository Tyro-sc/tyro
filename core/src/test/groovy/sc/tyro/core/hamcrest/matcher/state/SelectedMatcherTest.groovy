package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.SelectSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.selected

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Selected State Matcher")
class SelectedMatcherTest {
    @Test
    @DisplayName("Should support matcher Selected")
    void matcher() {
        SelectSupport cmp = mock(SelectSupport)

        // Selected
        when(cmp.selected()).thenReturn(true)
        assertThat(cmp, is(selected()))

        when(cmp.selected()).thenReturn(false)
        Error error = assertThrows(AssertionError, { assertThat(cmp, is(selected())) })
        assertThat(error.message, is('\nExpected: is selected\n     but: is unselected'))
    }
}
