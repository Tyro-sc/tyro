package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.hamcrest.Matchers
import sc.tyro.core.support.property.MaximumSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Maximum Property Matcher")
class MaximumMatcherTest {
    @Test
    @DisplayName("Should support matcher Maximum")
    void matcher() {
        MaximumSupport cmp = mock(MaximumSupport)

        when(cmp.maximum()).thenReturn(10)
        assertThat(cmp, has(Matchers.maximum(10)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(Matchers.maximum(50))) })
        assertThat(error.message, is('\nExpected: has maximum 50\n     but: has maximum 10'))
    }
}
