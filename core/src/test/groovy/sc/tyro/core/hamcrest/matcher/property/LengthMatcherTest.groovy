package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.LengthSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.length

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Length Property Matcher")
class LengthMatcherTest {
    @Test
    @DisplayName("Should support matcher Length")
    void matcher() {
        LengthSupport cmp = mock(LengthSupport)

        when(cmp.length()).thenReturn(10)
        assertThat(cmp, has(length(10)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(length(50))) })
        assertThat(error.message, is('\nExpected: has length 50\n     but: has length 10'))
    }
}
