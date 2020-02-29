package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import sc.tyro.core.hamcrest.Matchers
import sc.tyro.core.support.property.LabelSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Label Property Matcher")
class LabelMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        LabelSupport cmp = mock(LabelSupport)
        Mockito.when(cmp.label()).thenReturn('MyLabel')

        assertThat(cmp, has(Matchers.label('MyLabel')))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(Matchers.label('OtherLabel')))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has label "OtherLabel"\n     but: has label "MyLabel"'))
    }
}
