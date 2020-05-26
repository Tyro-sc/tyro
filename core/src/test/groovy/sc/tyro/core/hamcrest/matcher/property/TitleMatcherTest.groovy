package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.hamcrest.Matchers
import sc.tyro.core.support.property.TitleSupport

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
@DisplayName("Title Property Matcher")
class TitleMatcherTest {
    @Test
    @DisplayName("Should support matcher Title")
    void matcher() {
        TitleSupport cmp = mock(TitleSupport)
        when(cmp.title()).thenReturn('MyTitle')

        assertThat(cmp, has(Matchers.title('MyTitle')))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(Matchers.title('OtherTitle'))) })
        assertThat(error.message, is('\nExpected: has title "OtherTitle"\n     but: has title "MyTitle"'))
    }
}
