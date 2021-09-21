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

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Row Size Property Matcher")
class RowSizeMatcherTest {
    @Test
    @DisplayName("Should support matcher RowSize")
    void matcher() {
        RowSupport cmp = mock(RowSupport)

        when(cmp.rows()).thenReturn([mock(Row), mock(Row)])

        assertThat(cmp, has(2.rows))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(3.rows)) })
        assertThat(error.message, is('\nExpected: has 3 row(s)\n     but: has 2 row(s)'))
    }
}
