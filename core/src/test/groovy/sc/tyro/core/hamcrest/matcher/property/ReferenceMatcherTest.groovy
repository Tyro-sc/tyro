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
import sc.tyro.core.hamcrest.Matchers
import sc.tyro.core.support.property.ReferenceSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.endingWith
import static sc.tyro.core.hamcrest.Matchers.followingPattern
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.reference
import static sc.tyro.core.hamcrest.Matchers.startingWith
import static sc.tyro.core.hamcrest.Matchers.text
import static sc.tyro.core.hamcrest.Matchers.text
import static sc.tyro.core.hamcrest.Matchers.text

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Reference Property Matcher")
class ReferenceMatcherTest {
    @Test
    @DisplayName("Should support matcher Reference")
    void matcher() {
        ReferenceSupport cmp = mock(ReferenceSupport)

        when(cmp.reference()).thenReturn('my-reference')
        assertThat(cmp, has(reference('my-reference')))
        assertThat(cmp, has(reference(startingWith('my'))))
        assertThat(cmp, has(reference(endingWith('reference'))))
        assertThat(cmp, has(reference(followingPattern('^[a-zA-Z-]*'))))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(reference('other-reference'))) })
        assertThat(error.message, is('\nExpected: has reference "other-reference"\n     but: has reference "my-reference"'))
    }
}
