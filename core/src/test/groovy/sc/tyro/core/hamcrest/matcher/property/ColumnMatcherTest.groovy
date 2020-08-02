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
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.support.property.ColumnSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.columns
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Column Property Matcher")
class ColumnMatcherTest {
    @Test
    @DisplayName("Should support matcher Column")
    void matcher() {
        ColumnSupport cmp = mock(ColumnSupport)

        Column column_1 = mock(Column)
        when(column_1.title()).thenReturn('column_1')
        Column column_2 = mock(Column)
        when(column_2.title()).thenReturn('column_2')
        Column column_3 = mock(Column)
        when(column_3.title()).thenReturn('column_3')

        when(cmp.columns()).thenReturn([column_1, column_2])

        assertThat(cmp, has(columns('column_1', 'column_2')))
        assertThat(cmp, has(columns(column_1, column_2)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(columns('column_1', 'column_3'))) })
        assertThat(error.message, is('\nExpected: has column(s) ["column_1", "column_3"]\n     but: has column(s) ["column_1", "column_2"]'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(columns(column_1, column_3))) })
        assertThat(error.message, is('\nExpected: has column(s) ["column_1", "column_3"]\n     but: has column(s) ["column_1", "column_2"]'))
    }
}
