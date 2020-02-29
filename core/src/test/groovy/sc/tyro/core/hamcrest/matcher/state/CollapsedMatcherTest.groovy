package sc.tyro.core.hamcrest.matcher.state


import org.hamcrest.Matchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.CollapseSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.collapsed

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Collapsed State Matcher")
class CollapsedMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        CollapseSupport cmp = mock(CollapseSupport)

        when(cmp.collapsed()).thenReturn(true)
        assertThat(cmp, Matchers.is(collapsed()))

        when(cmp.collapsed()).thenReturn(false)

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, is(collapsed()))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: is collapsed\n     but: is expanded'))
    }
}
