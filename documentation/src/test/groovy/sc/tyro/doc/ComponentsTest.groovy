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
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Button
import sc.tyro.core.component.Image
import sc.tyro.core.component.Panel
import sc.tyro.core.component.field.TextField
import sc.tyro.bundle.html5.input.TextArea

@Disabled
class ComponentsTest {
    @Test
    void should_have_expected_properties_and_states_supported_by_button() {
        Button button = $('') as Button
        // tag::button[]
        button.should { have text('My Button Text') }
        // end::button[]
    }

    @Test
    void should_be_able_to_interact_with_mouse() {
        Button button = $('') as Button
        Image image = $('') as Image
        Panel panel = $('') as Panel

        // tag::mouse[]
        clickOn button
        doubleClickOn button
        rightClickOn button
        hoveringMouseOn button
        drag image on panel
        // end::mouse[]
    }

    @Test
    void should_be_able_to_interact_with_keyboard() {
        // tag::keyboard[]
        type('tyro') // tyro
        type(SHIFT + 'tyro') // => TYRO
        type(CTRL + ALT + SHIFT + 'x')
        // end::keyboard[]
    }

    @Test
    void test_text_area() {
        // tag::text_area[]
        TextField textarea = $('') as TextArea
        textarea.should {have length(350)}
        // end::text_area[]
    }
}
