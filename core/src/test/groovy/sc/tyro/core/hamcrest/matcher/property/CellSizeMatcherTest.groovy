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

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.support.property.CellSupport

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
@DisplayName("Cell Size Property Matcher")
class CellSizeMatcherTest {
    @Test
    @DisplayName("Should support matcher CellSize")
    void matcher() {
        CellSupport cmp = mock(CellSupport)

        when(cmp.cells()).thenReturn([mock(Cell), mock(Cell)])

        assertThat(cmp, has(2.cells))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(3.cells)) }) as AssertionError
        assertThat(error.message, is('\nExpected: has 3 cell(s)\n     but: has 2 cell(s)'))
    }
}
