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
package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.disabled

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Disabled State Matcher")
class DisabledMatcherTest {
    @Test
    @DisplayName("Should support matcher Disabled")
    void matcher() {
        Component cmp = mock(Component)

        when(cmp.enabled()).thenReturn(false)
        assertThat(cmp, is(disabled()))

        when(cmp.enabled()).thenReturn(true)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(disabled())) })
        assertThat(error.message, is('\nExpected: is disabled\n     but: is enabled'))
    }
}
