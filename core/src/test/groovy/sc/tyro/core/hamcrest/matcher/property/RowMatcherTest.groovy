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
package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.datagrid.Row
import sc.tyro.core.support.property.RowSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.rows

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Row Property Matcher")
class RowMatcherTest {
    @Test
    @DisplayName("Should support matcher Row")
    void matcher() {
        RowSupport cmp = mock(RowSupport)

        Row row_1 = mock(Row)
        when(row_1.title()).thenReturn('row_1')
        Row row_2 = mock(Row)
        when(row_2.title()).thenReturn('row_2')
        Row row_3 = mock(Row)
        when(row_3.title()).thenReturn('row_3')

        when(cmp.rows()).thenReturn([row_1, row_2])

        assertThat(cmp, has(rows('row_1', 'row_2')))
        assertThat(cmp, has(rows(row_1, row_2)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(rows('row_1', 'row_3'))) })
        assertThat(error.message, is('\nExpected: has row(s) ["row_1", "row_3"]\n     but: has row(s) ["row_1", "row_2"]'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(rows(row_1, row_3))) })
        assertThat(error.message, is('\nExpected: has row(s) ["row_1", "row_3"]\n     but: has row(s) ["row_1", "row_2"]'))
    }
}
