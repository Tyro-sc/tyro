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
import sc.tyro.core.support.property.TitleSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.endingWith
import static sc.tyro.core.hamcrest.Matchers.followingPattern
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.startingWith
import static sc.tyro.core.hamcrest.Matchers.text
import static sc.tyro.core.hamcrest.Matchers.text
import static sc.tyro.core.hamcrest.Matchers.text
import static sc.tyro.core.hamcrest.Matchers.title

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Title Property Matcher")
class TitleMatcherTest {
    @Test
    @DisplayName("Should support matcher Title")
    void matcher() {
        TitleSupport cmp = mock(TitleSupport)
        when(cmp.title()).thenReturn('My Title')

        assertThat(cmp, has(title('My Title')))
        assertThat(cmp, has(title(startingWith('My'))))
        assertThat(cmp, has(title(endingWith('Title'))))
        assertThat(cmp, has(title(followingPattern('^[a-zA-Z ]*'))))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(title('Other Title'))) })
        assertThat(error.message, is('\nExpected: has title "Other Title"\n     but: has title "My Title"'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(title(startingWith('prefix')))) })
        assertThat(error.message, is('\nExpected: has title starting with "prefix"\n     but: has title "My Title"'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(title(endingWith('suffix')))) })
        assertThat(error.message, is('\nExpected: has title ending with "suffix"\n     but: has title "My Title"'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(title(endingWith('[1-9]*')))) })
        assertThat(error.message, is('\nExpected: has title following pattern "[1-9]*"\n     but: has title "My Title"'))

    }
}
