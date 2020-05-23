package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.InputSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.placeholder

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Placeholder Property Matcher")
class PlaceholderMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        InputSupport cmp = mock(InputSupport)

        when(cmp.placeholder()).thenReturn('MyPlaceholder')
        assertThat(cmp, has(placeholder('MyPlaceholder')))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(placeholder('OtherPlaceholder'))) })
        assertThat(error.message, is('\nExpected: has placeholder "OtherPlaceholder"\n     but: has placeholder "MyPlaceholder"'))
    }
}
