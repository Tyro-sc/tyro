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
import sc.tyro.core.support.property.StepSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.step

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Step Property Matcher")
class StepMatcherTest {
    @Test
    @DisplayName("Should support matcher Step")
    void matcher() {
        StepSupport cmp = mock(StepSupport)

        when(cmp.step()).thenReturn(10)
        assertThat(cmp, has(step(10)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(step(50))) })
        assertThat(error.message, is('\nExpected: has step 50\n     but: has step 10'))
    }
}
