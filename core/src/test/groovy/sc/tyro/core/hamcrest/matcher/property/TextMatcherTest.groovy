package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.TextSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.text

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Text Property Matcher")
class TextMatcherTest {
    @Test
    @DisplayName("Should support matcher Text")
    void matcher() {
        TextSupport cmp = mock(TextSupport)
        when(cmp.text()).thenReturn('MyText')

        assertThat(cmp, has(text('MyText')))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(text('OtherText'))) })
        assertThat(error.message, is('\nExpected: has text "OtherText"\n     but: has text "MyText"'))
    }
}
