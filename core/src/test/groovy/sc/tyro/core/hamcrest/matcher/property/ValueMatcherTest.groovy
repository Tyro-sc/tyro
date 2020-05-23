package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ValueSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.value

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Value Property Matcher")
class ValueMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ValueSupport cmp = mock(ValueSupport)
        when(cmp.value()).thenReturn('MyValue')

        assertThat(cmp, has(value('MyValue')))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(value('OtherValue'))) })
        assertThat(error.message, is('\nExpected: has value "OtherValue"\n     but: has value "MyValue"'))
    }
}
