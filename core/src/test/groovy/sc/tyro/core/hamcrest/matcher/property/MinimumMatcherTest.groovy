package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.MinimumSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.minimum

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Minimum Property Matcher")
class MinimumMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        MinimumSupport cmp = mock(MinimumSupport)

        when(cmp.minimum()).thenReturn(10)
        assertThat(cmp, has(minimum(10)))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(minimum(50)))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has minimum 50\n     but: has minimum 10'))
    }
}
