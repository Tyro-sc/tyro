package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.StepSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.step

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Step Property Matcher")
class StepMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        StepSupport cmp = mock(StepSupport)

        when(cmp.step()).thenReturn(10)
        assertThat(cmp, has(step(10)))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(step(50)))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has step 50\n     but: has step 10'))
    }
}
