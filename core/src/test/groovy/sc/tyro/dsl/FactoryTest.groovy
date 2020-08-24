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
package sc.tyro.dsl

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.Provider
import sc.tyro.core.component.Button
import sc.tyro.core.component.CheckBox
import sc.tyro.core.component.Dropdown
import sc.tyro.core.component.Group
import sc.tyro.core.component.Heading
import sc.tyro.core.component.Item
import sc.tyro.core.component.Link
import sc.tyro.core.component.ListBox
import sc.tyro.core.component.Panel
import sc.tyro.core.component.Radio
import sc.tyro.core.component.field.ColorField
import sc.tyro.core.component.field.DateField
import sc.tyro.core.component.field.DateTimeField
import sc.tyro.core.component.field.EmailField
import sc.tyro.core.component.field.MonthField
import sc.tyro.core.component.field.NumberField
import sc.tyro.core.component.field.PasswordField
import sc.tyro.core.component.field.PhoneField
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.SearchField
import sc.tyro.core.component.field.TextField
import sc.tyro.core.component.field.TimeField
import sc.tyro.core.component.field.URLField
import sc.tyro.core.component.field.WeekField

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Factory Tests")
class FactoryTest {
    @BeforeEach
    void setUp() {
        provider = mock(Provider)
    }

    // =================  By Text   ======================

    @Test
    @DisplayName("Should find button by text")
    void findButtonByText() {
        Button button_1 = mock(Button)
        when(button_1.text()).thenReturn('Ok')
        Button button_2 = mock(Button)
        when(button_2.text()).thenReturn('!Ok')

        when(provider.findAll(Button)).thenReturn(List.of(button_1, button_2))

        button("Ok").should { be available }

        // Should fail if more than on match
        when(button_2.text()).thenReturn('Ok')


        IllegalStateException error = assertThrows(IllegalStateException, { button("Ok").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Button with text 'Ok'."))
    }

    @Test
    @DisplayName("Should find heading by text")
    void findHeadingByText() {
        Heading heading_1 = mock(Heading)
        when(heading_1.text()).thenReturn('Title')
        Heading heading_2 = mock(Heading)
        when(heading_2.text()).thenReturn('!Title')

        when(provider.findAll(Heading)).thenReturn(List.of(heading_1, heading_2))

        heading("Title").should { be available }

        // Should fail if more than on match
        when(heading_2.text()).thenReturn('Title')


        IllegalStateException error = assertThrows(IllegalStateException, { heading("Title").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Heading with text 'Title'."))
    }

    @Test
    @DisplayName("Should find link by text")
    void findLinkByText() {
        Link link_1 = mock(Link)
        when(link_1.text()).thenReturn('Link')
        Link link_2 = mock(Link)
        when(link_2.text()).thenReturn('!Link')

        when(provider.findAll(Link)).thenReturn(List.of(link_1, link_2))

        link("Link").should { be available }

        // Should fail if more than on match
        when(link_2.text()).thenReturn('Link')

        IllegalStateException error = assertThrows(IllegalStateException, { link("Link").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Link with text 'Link'."))
    }

    // =================  By Label  ======================

    @Test
    @DisplayName("Should find radio by label")
    void findRadioByLabel() {
        Radio radio_1 = mock(Radio)
        when(radio_1.label()).thenReturn('Label')
        Radio radio_2 = mock(Radio)
        when(radio_2.label()).thenReturn('!Label')

        when(provider.findAll(Radio)).thenReturn(List.of(radio_1, radio_2))

        // Should fail if more than on match
        when(radio_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { radio("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Radio with label 'Label'."))
    }

    @Test
    @DisplayName("Should find checkbox by label")
    void findCheckboxByLabel() {
        CheckBox checkBox_1 = mock(CheckBox)
        when(checkBox_1.label()).thenReturn('Label')
        CheckBox checkBox_2 = mock(CheckBox)
        when(checkBox_2.label()).thenReturn('!Label')

        when(provider.findAll(CheckBox)).thenReturn(List.of(checkBox_1, checkBox_2))

        // Should fail if more than on match
        when(checkBox_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { checkbox("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) CheckBox with label 'Label'."))
    }

    @Test
    @DisplayName("Should find dropdown by label")
    void findDropdownByLabel() {
        Dropdown dropdown_1 = mock(Dropdown)
        when(dropdown_1.label()).thenReturn('Label')
        Dropdown dropdown_2 = mock(Dropdown)
        when(dropdown_2.label()).thenReturn('!Label')

        when(provider.findAll(Dropdown)).thenReturn(List.of(dropdown_1, dropdown_2))

        // Should fail if more than on match
        when(dropdown_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { dropdown("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Dropdown with label 'Label'."))
    }

    @Test
    @DisplayName("Should find listBox by label")
    void findListBoxByLabel() {
        ListBox listBox_1 = mock(ListBox)
        when(listBox_1.label()).thenReturn('Label')
        ListBox listBox_2 = mock(ListBox)
        when(listBox_2.label()).thenReturn('!Label')

        when(provider.findAll(ListBox)).thenReturn(List.of(listBox_1, listBox_2))

        // Should fail if more than on match
        when(listBox_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { listBox("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) ListBox with label 'Label'."))
    }

    @Test
    @DisplayName("Should find password field by label")
    void findPasswordFieldByLabel() {
        PasswordField passwordField_1 = mock(PasswordField)
        when(passwordField_1.label()).thenReturn('Label')
        PasswordField passwordField_2 = mock(PasswordField)
        when(passwordField_2.label()).thenReturn('!Label')

        when(provider.findAll(PasswordField)).thenReturn(List.of(passwordField_1, passwordField_2))

        // Should fail if more than on match
        when(passwordField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { passwordField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) PasswordField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find text field by label")
    void findTextFieldByLabel() {
        TextField textField_1 = mock(TextField)
        when(textField_1.label()).thenReturn('Label')
        TextField textField_2 = mock(TextField)
        when(textField_2.label()).thenReturn('!Label')

        when(provider.findAll(TextField)).thenReturn(List.of(textField_1, textField_2))

        // Should fail if more than on match
        when(textField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { textField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) TextField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find search field by label")
    void findSearchFieldByLabel() {
        SearchField searchField_1 = mock(SearchField)
        when(searchField_1.label()).thenReturn('Label')
        SearchField searchField_2 = mock(SearchField)
        when(searchField_2.label()).thenReturn('!Label')

        when(provider.findAll(SearchField)).thenReturn(List.of(searchField_1, searchField_2))

        // Should fail if more than on match
        when(searchField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { searchField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) SearchField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find email field by label")
    void findEmailFieldByLabel() {
        EmailField emailField_1 = mock(EmailField)
        when(emailField_1.label()).thenReturn('Label')
        EmailField emailField_2 = mock(EmailField)
        when(emailField_2.label()).thenReturn('!Label')

        when(provider.findAll(EmailField)).thenReturn(List.of(emailField_1, emailField_2))

        // Should fail if more than on match
        when(emailField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { emailField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) EmailField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find url field by label")
    void findURLFieldByLabel() {
        URLField urlField_1 = mock(URLField)
        when(urlField_1.label()).thenReturn('Label')
        URLField urlField_2 = mock(URLField)
        when(urlField_2.label()).thenReturn('!Label')

        when(provider.findAll(URLField)).thenReturn(List.of(urlField_1, urlField_2))

        // Should fail if more than on match
        when(urlField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { urlField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) URLField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find number field by label")
    void findNumberFieldByLabel() {
        NumberField numberField_1 = mock(NumberField)
        when(numberField_1.label()).thenReturn('Label')
        NumberField numberField_2 = mock(NumberField)
        when(numberField_2.label()).thenReturn('!Label')

        when(provider.findAll(NumberField)).thenReturn(List.of(numberField_1, numberField_2))

        // Should fail if more than on match
        when(numberField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { numberField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) NumberField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find range field by label")
    void findRangeFieldByLabel() {
        RangeField rangeField_1 = mock(RangeField)
        when(rangeField_1.label()).thenReturn('Label')
        RangeField rangeField_2 = mock(RangeField)
        when(rangeField_2.label()).thenReturn('!Label')

        when(provider.findAll(RangeField)).thenReturn(List.of(rangeField_1, rangeField_2))

        // Should fail if more than on match
        when(rangeField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { rangeField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) RangeField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find date field by label")
    void findDateFieldByLabel() {
        DateField dateField_1 = mock(DateField)
        when(dateField_1.label()).thenReturn('Label')
        DateField dateField_2 = mock(DateField)
        when(dateField_2.label()).thenReturn('!Label')

        when(provider.findAll(DateField)).thenReturn(List.of(dateField_1, dateField_2))

        // Should fail if more than on match
        when(dateField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { dateField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) DateField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find color field by label")
    void findColorFieldByLabel() {
        ColorField colorField_1 = mock(ColorField)
        when(colorField_1.label()).thenReturn('Label')
        ColorField colorField_2 = mock(ColorField)
        when(colorField_2.label()).thenReturn('!Label')

        when(provider.findAll(ColorField)).thenReturn(List.of(colorField_1, colorField_2))

        // Should fail if more than on match
        when(colorField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { colorField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) ColorField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find date time field by label")
    void findDateTimeFieldByLabel() {
        DateTimeField dateTimeField_1 = mock(DateTimeField)
        when(dateTimeField_1.label()).thenReturn('Label')
        DateTimeField dateTimeField_2 = mock(DateTimeField)
        when(dateTimeField_2.label()).thenReturn('!Label')

        when(provider.findAll(DateTimeField)).thenReturn(List.of(dateTimeField_1, dateTimeField_2))

        // Should fail if more than on match
        when(dateTimeField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { dateTimeField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) DateTimeField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find month field by label")
    void findMonthFieldByLabel() {
        MonthField monthField_1 = mock(MonthField)
        when(monthField_1.label()).thenReturn('Label')
        MonthField monthField_2 = mock(MonthField)
        when(monthField_2.label()).thenReturn('!Label')

        when(provider.findAll(MonthField)).thenReturn(List.of(monthField_1, monthField_2))

        // Should fail if more than on match
        when(monthField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { monthField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) MonthField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find phone field by label")
    void findPhoneFieldByLabel() {
        PhoneField phoneField_1 = mock(PhoneField)
        when(phoneField_1.label()).thenReturn('Label')
        PhoneField phoneField_2 = mock(PhoneField)
        when(phoneField_2.label()).thenReturn('!Label')

        when(provider.findAll(PhoneField)).thenReturn(List.of(phoneField_1, phoneField_2))

        // Should fail if more than on match
        when(phoneField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { phoneField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) PhoneField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find time field by label")
    void findTimeFieldByLabel() {
        TimeField timeField_1 = mock(TimeField)
        when(timeField_1.label()).thenReturn('Label')
        TimeField timeField_2 = mock(TimeField)
        when(timeField_2.label()).thenReturn('!Label')

        when(provider.findAll(TimeField)).thenReturn(List.of(timeField_1, timeField_2))

        // Should fail if more than on match
        when(timeField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { timeField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) TimeField with label 'Label'."))
    }

    @Test
    @DisplayName("Should find week field by label")
    void findWeekFieldByLabel() {
        WeekField weekField_1 = mock(WeekField)
        when(weekField_1.label()).thenReturn('Label')
        WeekField weekField_2 = mock(WeekField)
        when(weekField_2.label()).thenReturn('!Label')

        when(provider.findAll(WeekField)).thenReturn(List.of(weekField_1, weekField_2))

        // Should fail if more than on match
        when(weekField_2.label()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { weekField("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) WeekField with label 'Label'."))
    }

    // =================  By Value  ======================

    @Test
    @DisplayName("Should find group by value")
    void findGroupByValue() {
        Group group_1 = mock(Group)
        when(group_1.value()).thenReturn('Value')
        Group group_2 = mock(Group)
        when(group_2.value()).thenReturn('!Value')

        when(provider.findAll(Group)).thenReturn(List.of(group_1, group_2))

        // Should fail if more than on match
        when(group_2.value()).thenReturn('Value')

        IllegalStateException error = assertThrows(IllegalStateException, { group("Value").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Group with value 'Value'."))
    }

    @Test
    @DisplayName("Should find item by value")
    void findItemByValue() {
        Item item_1 = mock(Item)
        when(item_1.value()).thenReturn('Value')
        Item item_2 = mock(Item)
        when(item_2.value()).thenReturn('!Value')

        when(provider.findAll(Item)).thenReturn(List.of(item_1, item_2))

        // Should fail if more than on match
        when(item_2.value()).thenReturn('Value')

        IllegalStateException error = assertThrows(IllegalStateException, { item("Value").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Item with value 'Value'."))
    }

    // =================  By Title  ======================

    @Test
    @DisplayName("Should find panel by title")
    void findPanelByTitle() {
        Panel panel_1 = mock(Panel)
        when(panel_1.title()).thenReturn('Title')
        Panel panel_2 = mock(Panel)
        when(panel_2.title()).thenReturn('!Title')

        when(provider.findAll(Panel)).thenReturn(List.of(panel_1, panel_2))

        // Should fail if more than on match
        when(panel_2.title()).thenReturn('Title')

        IllegalStateException error = assertThrows(IllegalStateException, { panel("Title").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Panel with title 'Title'."))
    }
}