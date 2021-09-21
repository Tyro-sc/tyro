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
import sc.tyro.core.component.Group
import sc.tyro.core.support.property.GroupSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.groups
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Group Property Matcher")
class GroupMatcherTest {
    @Test
    @DisplayName("Should support matcher Group")
    void matcher() {
        GroupSupport cmp = mock(GroupSupport)
        Group group_1 = mock(Group)
        when(group_1.value()).thenReturn('group_1')
        Group group_2 = mock(Group)
        when(group_2.value()).thenReturn('group_2')
        Group group_3 = mock(Group)
        when(group_3.value()).thenReturn('group_3')

        when(cmp.groups()).thenReturn([group_1, group_2])

        assertThat(cmp, has(groups('group_1', 'group_2')))
        assertThat(cmp, has(groups(group_1, group_2)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(groups('group_1', 'group_3'))) })
        assertThat(error.message, is('\nExpected: has group(s) ["group_1", "group_3"]\n     but: has group(s) ["group_1", "group_2"]'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(groups(group_1, group_3))) })
        assertThat(error.message, is('\nExpected: has group(s) ["group_1", "group_3"]\n     but: has group(s) ["group_1", "group_2"]'))
    }
}
