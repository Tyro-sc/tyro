package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.hamcrest.Matchers
import sc.tyro.core.support.property.ReferenceSupport

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
@DisplayName("Reference Property Matcher")
class ReferenceMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ReferenceSupport cmp = mock(ReferenceSupport)

        when(cmp.reference()).thenReturn('my-reference')
        assertThat(cmp, has(Matchers.reference('my-reference')))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(Matchers.reference('other-reference'))) })
        assertThat(error.message, is('\nExpected: has reference "other-reference"\n     but: has reference "my-reference"'))
    }
}
