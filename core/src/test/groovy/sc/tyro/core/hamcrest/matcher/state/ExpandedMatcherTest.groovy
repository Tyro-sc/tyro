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
package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.CollapseSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.expanded

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Expanded State Matcher")
class ExpandedMatcherTest {
    @Test
    @DisplayName("Should support matcher Expanded")
    void matcher() {
        CollapseSupport cmp = mock(CollapseSupport)

        when(cmp.expanded()).thenReturn(true)
        assertThat(cmp, is(expanded()))

        when(cmp.expanded()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(expanded())) })
        assertThat(error.message, is('\nExpected: is expanded\n     but: is collapsed'))
    }
}
