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
package sc.tyro.core.hamcrest.matcher.property.dummy

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

@DisplayName("Test Dummy Group")
class DummyGroupTest {
    @Test
    @DisplayName("Should store value")
    void value() {
        DummyGroup group = new DummyGroup('Group Value')

        assertThat(group.value(), is('Group Value'))
    }

    @Test
    @DisplayName("Should store other properties with default values")
    void defaultValue() {
        DummyGroup group = new DummyGroup('Group Value')

        assertThat(group.items(), is(empty()))
        assertThat(group.item(""), is(nullValue()))
    }
}