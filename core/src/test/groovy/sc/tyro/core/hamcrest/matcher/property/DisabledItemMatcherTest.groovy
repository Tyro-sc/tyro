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
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.selectedItem

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Disabled Item Property Matcher")
class DisabledItemMatcherTest {
    @Test
    @DisplayName("Should support matcher DisabledItem")
    void matcher() {
        ItemSupport cmp = mock(ItemSupport)
        Item item_1 = mock(Item)
        Item item_2 = mock(Item)

        when(item_1.value()).thenReturn('item_1_value')
        when(item_1.enabled()).thenReturn(false)
        when(item_2.value()).thenReturn('item_2_value')
        when(item_2.enabled()).thenReturn(true)

        when(cmp.items()).thenReturn([item_1, item_2])

        assertThat(cmp, has(disabledItem('item_1_value')))
        assertThat(cmp, has(disabledItem(item_1)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(disabledItem('item_2_value'))) })
        MatcherAssert.assertThat(error.message, is('\nExpected: has disabled item "item_2_value"\n     but: has disabled item "item_1_value"'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(disabledItem(item_2))) })
        MatcherAssert.assertThat(error.message, is('\nExpected: has disabled item "item_2_value"\n     but: has disabled item "item_1_value"'))
    }
}
