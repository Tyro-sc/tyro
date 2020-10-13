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

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.A
import sc.tyro.bundle.html5.Img
import sc.tyro.bundle.html5.input.InputTypeRange
import sc.tyro.bundle.html5.list.Ul
import sc.tyro.bundle.html5.table.Table
import sc.tyro.core.component.*
import sc.tyro.core.component.datagrid.DataGrid
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.TextField

import static sc.tyro.core.Tyro.*

@ExtendWith(TyroExtension)
class ComponentTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Button")
    void button() {
        Button button = button('Submit')
        // tag::button[]
        button.should { have text('Submit') }
        // end::button[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Checkbox")
    void checkbox() {
        CheckBox checkbox_1 = checkbox('1')
        CheckBox checkbox_2 = checkbox('2')
        CheckBox checkbox_3 = checkbox('3 (disabled)')
        // tag::checkBox[]
        checkbox_1.should {
            have label('1')
            be enabled
            be unchecked
        }

        checkbox_2.should {
            have label('2')
            be enabled
            be checked
        }

        checkbox_3.should {
            have label('3 (disabled)')
            be disabled
            be unchecked
        }
        // end::checkBox[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Radio")
    void radio() {
        Radio radio_1 = radio('1')
        Radio radio_2 = radio('2')
        Radio radio_3 = radio('3 (disabled)')
        // tag::radio[]
        radio_1.should {
            have label('1')
            be enabled
            be unchecked
        }

        radio_2.should {
            have label('2')
            be enabled
            be checked
        }

        radio_3.should {
            have label('3 (disabled)')
            be disabled
            be unchecked
        }
        // end::radio[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Dropdown")
    void dropdown() {
        Dropdown dropdown = dropdown('Language')
        // tag::dropdown[]
        dropdown.should {
            be visible
            have label('Language')
            have 2.items
            have items('EN', 'FR')
            have selectedItem('EN')
            0.groups
        }
        // end::dropdown[]
    }


    @Test
    @DisplayName("Should have expected states and properties supported by Group")
    void group() {
        Dropdown os = dropdown('OS')
        // tag::group[]
        os.should {
            have label('OS')
            have 5.items
            have 2.groups
        }

        os.group('Linux').should {
            have 3.items
            have items('Ubuntu', 'Debian', 'Gentoo')
        }

        os.group('Windows').should {
            have 2.items
            have items('8', '10')
        }
        // end::group[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by ListBox")
    void listBox() {
        ListBox cities = listBox('Cities')
        // tag::listBox[]
        cities.should {
            have label('Cities')
            have 4.items
            have 4.visibleItems
            have items('Montpellier', 'Montreal', 'New York', 'Boston')
            have selectedItems('Montpellier', 'Boston')
        }

        cities.item('New York').should { be disabled }
        // end::listBox[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by ListView")
    void listView() {
        ListView list = $('ul.list-group') as Ul
        // tag::listView[]
        list.should {
            have 5.items
        }
        // end::listView[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Item")
    void item() {
        ListBox cities = listBox('Cities')
        // tag::item[]
        cities.items()[0].should {
            have value('Montpellier')
            be selected
        }

        cities.item('New York').should {
            be disabled
            be unselected
        }
        // end::item[]

        ListView list = $('ul.list-group') as Ul
        // tag::item-listview[]
        list.items()[0].should { have value('Item 1') }
        list.item('Item 2').should { be visible }
        // end::item-listview[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Link")
    void link() {
        Link link = $('#link') as A
        // tag::link[]
        link.should {
            have text('My Link to Google')
            have reference('https://www.google.com/')
        }
        // end::link[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Form")
    void form() {
        Form form = $('#form') as sc.tyro.bundle.html5.Form
        // tag::form[]
        form.should { be invalid }
        // end::form[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by TextField")
    void textField() {
        TextField textField = field('Name')
        // tag::textField[]
        textField.should { have length(74) }
        // end::textField[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by RangeField")
    void rangeField() {
        RangeField rangeField = $('#range_field') as InputTypeRange
        // tag::rangeField[]
        rangeField.should {
            have minimum(0)
            have maximum(100)
            have value(76)
            have step(2)
            be inRange
        }
        // end::rangeField[]
    }

    @Test
    @DisplayName("Should have expected states and properties supported by Image")
    void image() {
        Image image = $('#image') as Img
        // tag::image[]
        image.should { have reference('http://localhost:8080/img/seahorse.jpg')}
        // end::image[]
    }


    @Test
    @DisplayName("Should have expected states and properties supported by DataGrid")
    void dataGrid() {
        DataGrid dataGrid = $('#datagrid') as Table
        // tag::dataGrid[]
        dataGrid.should {
            have 3.columns
            have 3.rows
        }
        // end::dataGrid[]

        // tag::column[]
        dataGrid.columns()[1].should {
            have title('Firstname')
            have 3.cells
        }
        // end::column[]

        // tag::row[]
        dataGrid.rows()[1].should {
            have title('2')
            have 2.cells
        }
        // end::row[]

        // tag::cell[]
        dataGrid.rows()[0].cells()[1].should {
            have value('Black')
        }
        // end::cell[]
    }

    // ========================================================

//
//    @Test
//    void should_be_able_to_interact_with_mouse() {
//        Button button = $('') as Button
//        Image image = $('') as Image
//        Panel panel = $('') as Panel
//
//        // tag::mouse[]
//        clickOn button
//        doubleClickOn button
//        rightClickOn button
//        hoveringMouseOn button
//        drag image on panel
//        // end::mouse[]
//    }

//    @Test
//    void should_be_able_to_interact_with_keyboard() {
//        // tag::keyboard[]
//        type('tyro') // tyro
//        type(SHIFT + 'tyro') // => TYRO
//        type(CTRL + ALT + SHIFT + 'x')
//        // end::keyboard[]
//    }

//    @Test
//    void test_text_area() {
//        // tag::text_area[]
//        TextField textarea = $('') as TextArea
//        textarea.should { have length(350) }
//        // end::text_area[]
//    }
}
