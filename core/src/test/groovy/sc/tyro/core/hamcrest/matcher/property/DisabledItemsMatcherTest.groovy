/**
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Item
import sc.tyro.core.support.property.ItemSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.disabledItem
import static sc.tyro.core.hamcrest.Matchers.disabledItems
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.selectedItems

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Disabled Items Property Matcher")
class DisabledItemsMatcherTest {
    @Test
    @DisplayName("Should support matcher DisabledItems")
    void matcher() {
        ItemSupport cmp = mock(ItemSupport)
        Item item_1 = mock(Item)
        Item item_2 = mock(Item)
        Item item_3 = mock(Item)
        Item item_4 = mock(Item)

        when(item_1.value()).thenReturn('item_1_value')
        when(item_1.enabled()).thenReturn(false)
        when(item_2.value()).thenReturn('item_2_value')
        when(item_2.enabled()).thenReturn(false)
        when(item_3.value()).thenReturn('item_3_value')
        when(item_3.enabled()).thenReturn(true)
        when(item_4.value()).thenReturn('item_4_value')
        when(item_4.enabled()).thenReturn(true)

        when(cmp.item('item_1_value')).thenReturn(item_1)
        when(cmp.item('item_2_value')).thenReturn(item_2)
        when(cmp.item('item_3_value')).thenReturn(item_3)
        when(cmp.item('item_4_value')).thenReturn(item_4)
        when(cmp.items()).thenReturn([item_1, item_2, item_3, item_4])

        assertThat(cmp, has(disabledItems('item_1_value', 'item_2_value')))
        assertThat(cmp, has(disabledItems(item_1, item_2)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(disabledItems('item_3_value', 'item_4_value'))) })
        MatcherAssert.assertThat(error.message, is('\nExpected: has disabled item(s) ["item_3_value", "item_4_value"]\n     but: has disabled item(s) ["item_1_value", "item_2_value"]'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(disabledItems(item_3, item_4))) })
        MatcherAssert.assertThat(error.message, is('\nExpected: has disabled item(s) ["item_3_value", "item_4_value"]\n     but: has disabled item(s) ["item_1_value", "item_2_value"]'))
    }
}
