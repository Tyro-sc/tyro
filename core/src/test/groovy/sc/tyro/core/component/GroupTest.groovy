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
package sc.tyro.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.property.ValueSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Group Component Tests")
class GroupTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Group in Component
        assert Group in ValueSupport
        assert Group in ItemSupport
    }

    @Test
    @DisplayName("Should have identity based on Value")
    void identity() {
        Group group_1 = new TestGroup('value_1')
        Group group_2 = new TestGroup('value_2')
        Group group_3 = new TestGroup('value_1')

        assert group_1 != group_2
        assert group_1 == group_3

        assert group_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestGroup extends Group {
        private String value

        TestGroup(String value) {
            this.value = value
        }

        List<Item> items() {
            return null
        }

        Item item(String value) {
            return null
        }

        Object value() {
            return value
        }
    }
}