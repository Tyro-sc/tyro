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
package sc.tyro.bundle.html5

import com.mitchtalmadge.asciidata.table.ASCIITable
import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.input.InputTypePassword
import sc.tyro.core.ComponentException
import sc.tyro.core.component.*
import sc.tyro.core.component.field.EmailField
import sc.tyro.core.component.field.Field
import sc.tyro.core.component.field.PasswordField
import sc.tyro.core.component.field.TextField
import sc.tyro.web.TyroWebTestExtension

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.endsWith
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.startsWith
import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.By.expression
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.*
import static sc.tyro.core.Tyro.visible
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
        List<Button> buttons = provider.findAll(Button, expression('.some-selector'))

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
        assert fields.size() == 6

        List<Button> buttons = findAll(Button)
        assert buttons.size() == 5
    }

    @Test
    @DisplayName("Should find field by label or placeholder")
    void findField() {
        Field field_1 = field('Email')
        field_1.should { have label('Email') }

        field_1 = field('Password', PasswordField)
        field_1.should { have label('Password') }

        EmailField email = field('Email', EmailField)
        email.should { have label('Email') }

        email = field('Enter your email', EmailField)
        email.should { have placeholder('Enter your email') }

        Field unavailable = field('Unavailable Button')
        unavailable.should { be missing }
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

    @Test
    @DisplayName("Should display beautiful message when factory not able to find expected component(s)")
    void beautifulErrorMessage() {
        ClassCastException error = assertThrows(GroovyCastException, { EmailField email = field('Password') })
        assertThat(error.message, startsWith("Cannot cast object 'InputTypePassword"))
        assertThat(error.message, endsWith("with class 'sc.tyro.bundle.html5.input.InputTypePassword' to class 'sc.tyro.core.component.field.EmailField'"))
        println error.message

        ComponentException exception = assertThrows(ComponentException, { field("Label1", PasswordField) })
        assertThat(exception.message, startsWith("Unable to find Component(s) sc.tyro.core.component.field.PasswordField with label or placeholder 'Label1'"))
        println exception.message

        exception = assertThrows(ComponentException, { field("Label2", TextField) })
        assertThat(exception.message, startsWith("Find 2 Component(s) sc.tyro.core.component.field.TextField with label or placeholder 'Label2'"))
        println exception.message

        sc.tyro.core.component.Button button = button('Unavailable Button')
        assertThat(button.available(), is(false))

        exception = assertThrows(ComponentException, { button.visible() })
        assertThat(exception.message, equalTo("Component defined by sc.tyro.core.component.Button with text 'Unavailable Button' not found."))
        println exception.message

        Field invalid = $('#invalid') as InputTypePassword
        exception = assertThrows(ComponentException, { invalid.visible() })
        assertThat(exception.message, equalTo("Component defined by expression: \$('#invalid') not found."))
        println exception.message
    }
}
