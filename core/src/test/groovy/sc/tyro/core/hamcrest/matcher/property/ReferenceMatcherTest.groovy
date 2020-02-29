package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import sc.tyro.core.hamcrest.Matchers
import sc.tyro.core.support.property.ReferenceSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Reference Property Matcher")
class ReferenceMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ReferenceSupport cmp = mock(ReferenceSupport)

        Mockito.when(cmp.reference()).thenReturn('my-reference')
        assertThat(cmp, has(Matchers.reference('my-reference')))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(Matchers.reference('other-reference')))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has reference "other-reference"\n     but: has reference "my-reference"'))
    }
}
