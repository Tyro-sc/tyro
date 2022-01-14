/*
 * Copyright © 2021 Ovea (d.avenante@gmail.com)
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
import static sc.tyro.core.hamcrest.Matchers.items

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Item Property Matcher")
class ItemMatcherTest {
    @Test
    @DisplayName("Should support matcher Item")
    void matcher() {
        ItemSupport cmp = mock(ItemSupport)

        Item item_1 = mock(Item)
        when(item_1.value()).thenReturn('item_1')
        Item item_2 = mock(Item)
        when(item_2.value()).thenReturn('item_2')
        Item item_3 = mock(Item)
        when(item_3.value()).thenReturn('item_3')

        when(cmp.item('item_1')).thenReturn(item_1)
        when(cmp.item('item_2')).thenReturn(item_2)
        when(cmp.items()).thenReturn([item_1, item_2])

        assertThat(cmp, has(items('item_1', 'item_2')))
        assertThat(cmp, has(items(item_1, item_2)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(items('item_1', 'item_3'))) })
        assertThat(error.message, is('\nExpected: has item(s) ["item_1", "item_3"]\n     but: has item(s) ["item_1", "item_2"]'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(items(item_1, item_3))) })
        assertThat(error.message, is('\nExpected: has item(s) ["item_1", "item_3"]\n     but: has item(s) ["item_1", "item_2"]'))
    }
}
