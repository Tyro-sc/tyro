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
@DisplayName("Unselected Item Size Property Matcher")
class UnSelectedItemsSizeMatcherTest {
    @Test
    @DisplayName("Should support matcher UnSelectedItemsSize")
    void matcher() {
        ItemSupport cmp = mock(ItemSupport)
        Item item_1 = mock(Item)
        when(item_1.selected()).thenReturn(false)
        Item item_2 = mock(Item)
        when(item_2.selected()).thenReturn(false)
        Item item_3 = mock(Item)
        when(item_3.selected()).thenReturn(true)

        when(cmp.items()).thenReturn([item_1, item_2, item_3])

        assertThat(cmp, has(2.unSelectedItems))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(3.unSelectedItems)) })
        assertThat(error.message, is('\nExpected: has 3 unselected item(s)\n     but: has 2 unselected item(s)'))
    }
}