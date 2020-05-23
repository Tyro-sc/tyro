package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.CollapseSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.expanded

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Expanded State Matcher")
class ExpandedMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        CollapseSupport cmp = mock(CollapseSupport)

        when(cmp.expanded()).thenReturn(true)
        assertThat(cmp, is(expanded()))

        when(cmp.expanded()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(expanded())) })
        assertThat(error.message, is('\nExpected: is expanded\n     but: is collapsed'))
    }
}
