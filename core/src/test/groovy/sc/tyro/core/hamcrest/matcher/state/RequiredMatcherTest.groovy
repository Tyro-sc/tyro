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
package sc.tyro.core.hamcrest.matcher.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.support.state.RequiredSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.required

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Required State Matcher")
class RequiredMatcherTest {
    @Test
    @DisplayName("Should support matcher Required")
    void matcher() {
        RequiredSupport cmp = mock(RequiredSupport)

        when(cmp.required()).thenReturn(true)
        assertThat(cmp, is(required()))

        when(cmp.required()).thenReturn(false)

        Error error = assertThrows(AssertionError, { assertThat(cmp, is(required())) })
        assertThat(error.message, is('\nExpected: is required\n     but: is optional'))
    }
}
