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
package sc.tyro.core.component.datagrid

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component
import sc.tyro.core.support.property.ValueSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Cell Component Tests")
class CellTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Cell in Component
        assert Cell in ValueSupport
    }

    @Test
    @DisplayName("Should have identity based on Value")
    void identity() {
        Cell cell_1 = new TestCell('value_1')
        Cell cell_2 = new TestCell('value_2')
        Cell cell_3 = new TestCell('value_1')

        assert cell_1 != cell_2
        assert cell_1 == cell_3

        assert cell_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestCell extends Cell {
        private String value

        TestCell(String value) {
            this.value = value
        }

        Object value() {
            return value
        }
    }
}