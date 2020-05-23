package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Item
import sc.tyro.core.support.property.SelectedItemsSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.selectedItems

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Selected Items Property Matcher")
class SelectedItemsMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        SelectedItemsSupport cmp = mock(SelectedItemsSupport)
        Item itemSelected_1 = mock(Item)
        Item itemSelected_2 = mock(Item)

        when(itemSelected_1.value()).thenReturn('selected_value_1')
        when(itemSelected_2.value()).thenReturn('selected_value_2')
        when(cmp.selectedItems()).thenReturn([itemSelected_1, itemSelected_2])

        assertThat(cmp, has(selectedItems('selected_value_1', 'selected_value_2')))
        assertThat(cmp, has(selectedItems(itemSelected_1, itemSelected_1)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(selectedItems('no_selected_item_1', 'no_selected_item_2'))) })
        assertThat(error.message, is('\nExpected: has selected item(s) ["no_selected_item_1", "no_selected_item_2"]\n     but: has selected item(s) ["selected_value_1", "selected_value_2"]'))

        Item item_1 = mock(Item)
        Item item_2 = mock(Item)
        when(item_1.value()).thenReturn('no_selected_item_1')
        when(item_2.value()).thenReturn('no_selected_item_2')

        error = assertThrows(AssertionError, { assertThat(cmp, has(selectedItems(item_1, item_2))) })
        assertThat(error.message, is('\nExpected: has selected item(s) ["no_selected_item_1", "no_selected_item_2"]\n     but: has selected item(s) ["selected_value_1", "selected_value_2"]'))
    }
}
