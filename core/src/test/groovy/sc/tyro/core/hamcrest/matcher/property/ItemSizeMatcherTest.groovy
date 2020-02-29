package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Item
import sc.tyro.core.support.property.ItemSupport

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
@DisplayName("Item Size Property Matcher")
class ItemSizeMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ItemSupport cmp = mock(ItemSupport)

        when(cmp.items()).thenReturn([mock(Item), mock(Item)])

        assertThat(cmp, has(2.items))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(3.items))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has 3 item(s)\n     but: has 2 item(s)'))
    }
}
