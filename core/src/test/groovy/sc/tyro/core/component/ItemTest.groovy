/*
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
package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ValueSupport
import sc.tyro.core.support.state.SelectSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Item Component Tests")
class ItemTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Item in Component
        assert Item in SelectSupport
        assert Item in ValueSupport
    }

    @Test
    @DisplayName("Should have identity based on Value")
    void identity() {
        Item item_1 = new TestItem('value_1')
        Item item_2 = new TestItem('value_2')
        Item item_3 = new TestItem('value_1')

        assert item_1 != item_2
        assert item_1 == item_3

        assert item_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestItem extends Item {
        private String value

        TestItem(String value) {
            this.value = value
        }

        Object value() {
            return value
        }

        boolean selected() {
            return false
        }
    }
}