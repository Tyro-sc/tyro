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
package sc.tyro.doc

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Radio

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.Tyro.*

@Disabled
class SpecTest {
    @Test
    @DisplayName("Gender spec")
    void gender() {
        Radio male_radio = mock(Radio)
        Radio female_radio = mock(Radio)

        when(male_radio.checked()).thenReturn(false, true, false)
        when(male_radio.label()).thenReturn('Male')
        when(male_radio.enabled()).thenReturn(true)

        when(female_radio.checked()).thenReturn(false, false, true)
        when(female_radio.label()).thenReturn('Female')
        when(female_radio.enabled()).thenReturn(true)

        // tag::gender-spec[]
        male_radio.should {
            be unchecked
            have label('Male')
        }

        female_radio.should {
            be unchecked
            have label('Female')
        }

        check male_radio
        male_radio.should { be checked }
        female_radio.should { be unchecked }

        check female_radio
        male_radio.should { be unchecked }
        female_radio.should { be checked }
        // end::gender-spec[]
    }
}