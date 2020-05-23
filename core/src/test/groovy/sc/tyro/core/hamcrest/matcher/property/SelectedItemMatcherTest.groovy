package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Item
import sc.tyro.core.support.property.SelectedItemSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.selectedItem

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Selected Item Property Matcher")
class SelectedItemMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        SelectedItemSupport cmp = mock(SelectedItemSupport)
        Item itemSelected = mock(Item)

        when(itemSelected.value()).thenReturn('selected')
        when(cmp.selectedItem()).thenReturn(itemSelected)

        assertThat(cmp, has(selectedItem('selected')))
        assertThat(cmp, has(selectedItem(itemSelected)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(selectedItem('no selected'))) })
        assertThat(error.message, is('\nExpected: has selected item "no selected"\n     but: has selected item "selected"'))

        Item item = mock(Item)
        when(item.value()).thenReturn('no selected')

        error = assertThrows(AssertionError, { assertThat(cmp, has(selectedItem(item))) })
        assertThat(error.message, is('\nExpected: has selected item "no selected"\n     but: has selected item "selected"'))
    }
}
