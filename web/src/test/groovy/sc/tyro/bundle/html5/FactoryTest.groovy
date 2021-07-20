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
package sc.tyro.bundle.html5

import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.component.CheckBox
import sc.tyro.core.component.Dropdown
import sc.tyro.core.component.Group
import sc.tyro.core.component.Heading
import sc.tyro.core.component.Item
import sc.tyro.core.component.Link
import sc.tyro.core.component.ListBox
import sc.tyro.core.component.Panel
import sc.tyro.core.component.Radio
import sc.tyro.core.component.field.EmailField
import sc.tyro.core.component.field.Field
import sc.tyro.core.component.field.PasswordField
import sc.tyro.web.TyroWebTestExtension

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.endsWith
import static org.hamcrest.Matchers.startsWith
import static org.junit.jupiter.api.Assertions.assertArrayEquals
import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.By.expression
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.*
import static sc.tyro.core.Tyro.radio
import static sc.tyro.core.Tyro.radio
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Factories Tests")
class FactoryTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'factory.html'
    }

    @Test
    @DisplayName("Should find unique component by expression")
    void find() {
        Button button = provider.find(Button, expression('button'))

        assert button.visible()
    }

    @Test
    @DisplayName("Should find all components by expression")
    void findAllByExpression() {
        List<Button> buttons = provider.findAll(Button, expression('.btn-primary'))

        assert buttons.size() == 4
    }

    @Test
    @DisplayName("Should find all components by type")
    void findAll() {
        // Abstract class
        List<sc.tyro.core.component.Button> componentFromAbstract = provider.findAll(sc.tyro.core.component.Button)

        assert componentFromAbstract.size() == 5

        // Concrete class
        List<Button> componentFromConcrete = provider.findAll(Button)

        assert componentFromConcrete.size() == 5
    }

    @Test
    @DisplayName("Should find components by type")
    void findByType() {
        List<Field> fields = findAll(Field)
        assert fields.size() == 2

        List<Button> buttons = findAll(Button)
        assert buttons.size() == 5
    }

    @Test
    @DisplayName("Should find field by label or placeholder")
    void findField() {
        Field field_1 = field('Email')
        assert field_1.label() == 'Email'

        field_1 = field('Password')
        assert field_1.label() == 'Password'

        EmailField email = field('Email', EmailField)
        assert email.label() == 'Email'

        email = field('Enter your email', EmailField)
        assert email.placeholder() == 'Enter your email'

        field('Unavailable Button').should { be missing }

        ClassCastException classCastError = assertThrows(GroovyCastException, { PasswordField password = field("Email", EmailField) })
        assertThat(classCastError.message, startsWith("Cannot cast object 'InputTypeEmail"))
        assertThat(classCastError.message, endsWith("with class 'sc.tyro.bundle.html5.input.InputTypeEmail' to class 'sc.tyro.core.component.field.PasswordField'"))
    }

    @Test
    @DisplayName("Should find button by text")
    void findButton() {
        sc.tyro.core.component.Button button = button('Button 1')
        assert button.text() == 'Button 1'
    }

    @Test
    @DisplayName("Should find item by value")
    void findItem() {
        Item item = item('Montpellier')
        assert item.value() == 'Montpellier'
    }

    @Test
    @DisplayName("Should find radio by value")
    void findRadio() {
        Radio radio_1 = radio('Male')
        assert radio_1.checked()

        Radio radio_2 = radio('Female')
        assert !radio_2.checked()
    }

    @Test
    @DisplayName("Should find checkbox by label")
    void findCheckbox() {
        CheckBox checkBox = checkbox('Check me out')
        assert !checkBox.checked()
    }

    @Test
    @DisplayName("Should find dropdown by label")
    void findDropdown() {
        Dropdown dropdown = dropdown('Cities')
        assert dropdown.items().size() == 2
    }

    @Test
    @DisplayName("Should find listBox by label")
    void findListBox() {
        ListBox listBox = listBox('Planets')
        assert listBox.items().size() == 3
    }

    @Test
    @DisplayName("Should find group by label")
    void findGroup() {
        Group group = group("planets")
        assert group.items().size() == 3
    }

    @Test
    @DisplayName("Should find Heading by text")
    void findHeading() {
        Heading heading = heading('ListBox')
        assert heading.text() == 'ListBox'
    }

    @Test
    @DisplayName("Should find Link by text")
    void findLink() {
        Link link = link('Nineteen Eighty-Four')
        assert link.reference() == 'https://www.george-orwell.org/1984/0.html'
    }
}
