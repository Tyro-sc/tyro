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
package sc.tyro.core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.*
import sc.tyro.core.component.field.*

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.*
import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Tyro base class Tests")
class TyroTest {
    private Provider provider

    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        Config.provider = provider
    }

    @Test
    @DisplayName("Should expose a convenient method to find a component by expression")
    void findOneWith$() {
        $('expression')

        verify(provider, times(1)).find(Component, By.expression('expression'))
    }

    @Test
    @DisplayName("Should expose a convenient method to find a list of components by expression")
    void findManyWith$$() {
        $$('expression', Button)

        verify(provider, times(1)).findAll(Button, By.expression('expression'))
    }

    @Test
    @DisplayName("Should find all components by type")
    void findAll() {
        findAll(Button)

        verify(provider, times(1)).findAll(Button)
    }

    @Test
    @DisplayName("Should find Button by text")
    void findButtonByText() {
        Button button_1 = mock(Button)
        when(button_1.text()).thenReturn('Ok')
        Button button_2 = mock(Button)
        when(button_2.text()).thenReturn('Ko')

        when(provider.findAll(Button)).thenReturn([button_1, button_2])

        Button button = button('Ok')

        assertThat(button.text(), is('Ok'))
    }

    @Test
    @DisplayName("Should find Radio by label")
    void findRadioByLabel() {
        Radio radio_1 = mock(Radio)
        when(radio_1.label()).thenReturn('label')
        Radio radio_2 = mock(Radio)
        when(radio_2.label()).thenReturn('lab')

        when(provider.findAll(Radio)).thenReturn([radio_1, radio_2])

        Radio radio = radio('label')

        assertThat(radio.label(), is('label'))
    }

    @Test
    @DisplayName("Should find CheckBox by label")
    void findCheckBoxByLabel() {
        CheckBox checkBox1 = mock(CheckBox)
        when(checkBox1.label()).thenReturn('label')
        CheckBox checkBox2 = mock(CheckBox)
        when(checkBox2.label()).thenReturn('lab')

        when(provider.findAll(CheckBox)).thenReturn([checkBox1, checkBox2])

        CheckBox checkBox = checkbox('label')

        assertThat(checkBox.label(), is('label'))
    }

    @Test
    @DisplayName("Should find Dropdown by label")
    void findDropdownByLabel() {
        Dropdown dropdown_1 = mock(Dropdown)
        when(dropdown_1.label()).thenReturn('label')
        Dropdown dropdown_2 = mock(Dropdown)
        when(dropdown_2.label()).thenReturn('lab')

        when(provider.findAll(Dropdown)).thenReturn([dropdown_1, dropdown_2])

        Dropdown dropdown = dropdown('label')

        assertThat(dropdown.label(), is('label'))
    }

    @Test
    @DisplayName("Should find ListBox by label")
    void findListBoxByLabel() {
        ListBox listBox_1 = mock(ListBox)
        when(listBox_1.label()).thenReturn('label')
        ListBox listBox_2 = mock(ListBox)
        when(listBox_2.label()).thenReturn('lab')

        when(provider.findAll(ListBox)).thenReturn([listBox_1, listBox_2])

        ListBox listBox = listBox('label')

        assertThat(listBox.label(), is('label'))
    }

    @Test
    @DisplayName("Should find Group by value")
    void findGroupByValue() {
        Group group_1 = mock(Group)
        when(group_1.value()).thenReturn('value')
        Group group_2 = mock(Group)
        when(group_2.value()).thenReturn('val')

        when(provider.findAll(Group)).thenReturn([group_1, group_2])

        Group group = group('value')

        assertThat(group.value(), is('value'))
    }

    @Test
    @DisplayName("Should find Item by value")
    void findItemByValue() {
        Item item_1 = mock(Item)
        when(item_1.value()).thenReturn('value')
        Item item_2 = mock(Item)
        when(item_2.value()).thenReturn('val')

        when(provider.findAll(Item)).thenReturn([item_1, item_2])

        Item item = item('value')

        assertThat(item.value(), is('value'))
    }

    @Test
    @DisplayName("Should find Heading by text")
    void findHeadingByText() {
        Heading heading_1 = mock(Heading)
        when(heading_1.text()).thenReturn('text')
        Heading heading_2 = mock(Heading)
        when(heading_2.text()).thenReturn('txt')

        when(provider.findAll(Heading)).thenReturn([heading_1, heading_2])

        Heading heading = heading('text')

        assertThat(heading.text(), is('text'))
    }

    @Test
    @DisplayName("Should find Panel by title")
    void findPanelByTitle() {
        Panel panel_1 = mock(Panel)
        when(panel_1.title()).thenReturn('title')
        Panel panel_2 = mock(Panel)
        when(panel_2.title()).thenReturn('')

        when(provider.findAll(Panel)).thenReturn([panel_1, panel_2])

        Panel panel = panel('title')

        assertThat(panel.title(), is('title'))
    }

    @Test
    @DisplayName("Should find Link by text")
    void findLinkByText() {
        Link link_1 = mock(Link)
        when(link_1.text()).thenReturn('text')
        Link link_2 = mock(Link)
        when(link_2.text()).thenReturn('txt')

        when(provider.findAll(Link)).thenReturn([link_1, link_2])

        Link link = link('text')

        assertThat(link.text(), is('text'))
    }

    @Test
    @DisplayName("Should find PasswordField by label")
    void findPasswordFieldByLabel() {
        PasswordField passwordField_1 = mock(PasswordField)
        when(passwordField_1.label()).thenReturn("label")
        PasswordField passwordField_2 = mock(PasswordField)
        when(passwordField_2.label()).thenReturn("lab")

        when(provider.findAll(PasswordField)).thenReturn([passwordField_1, passwordField_2])

        PasswordField passwordField = passwordField('label')

        assertThat(passwordField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find TextField by label")
    void findTextFieldByLabel() {
        TextField textField_1 = mock(TextField)
        when(textField_1.label()).thenReturn("label")
        TextField textField_2 = mock(TextField)
        when(textField_2.label()).thenReturn("lab")

        when(provider.findAll(TextField)).thenReturn([textField_1, textField_2])

        TextField textField = textField('label')

        assertThat(textField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find SearchField by label")
    void findSearchFieldByLabel() {
        SearchField searchField_1 = mock(SearchField)
        when(searchField_1.label()).thenReturn("label")
        SearchField searchField_2 = mock(SearchField)
        when(searchField_2.label()).thenReturn("lab")

        when(provider.findAll(SearchField)).thenReturn([searchField_1, searchField_2])

        SearchField searchField = searchField('label')

        assertThat(searchField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find EmailField by label")
    void findEmailFieldByLabel() {
        EmailField emailField_1 = mock(EmailField)
        when(emailField_1.label()).thenReturn("label")
        EmailField emailField_2 = mock(EmailField)
        when(emailField_2.label()).thenReturn("lab")

        when(provider.findAll(EmailField)).thenReturn([emailField_1, emailField_2])

        EmailField emailField = emailField('label')

        assertThat(emailField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find URLField by label")
    void findURLFieldByLabel() {
        URLField urlField_1 = mock(URLField)
        when(urlField_1.label()).thenReturn("label")
        URLField urlField_2 = mock(URLField)
        when(urlField_2.label()).thenReturn("lab")

        when(provider.findAll(URLField)).thenReturn([urlField_1, urlField_2])

        URLField urlField = urlField('label')

        assertThat(urlField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find NumberField by label")
    void findNumberFieldByLabel() {
        NumberField numberField_1 = mock(NumberField)
        when(numberField_1.label()).thenReturn("label")
        NumberField numberField_2 = mock(NumberField)
        when(numberField_2.label()).thenReturn("lab")

        when(provider.findAll(NumberField)).thenReturn([numberField_1, numberField_2])

        NumberField numberField = numberField('label')

        assertThat(numberField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find RangeField by label")
    void findRangeFieldByLabel() {
        RangeField rangeField_1 = mock(RangeField)
        when(rangeField_1.label()).thenReturn("label")
        RangeField rangeField_2 = mock(RangeField)
        when(rangeField_2.label()).thenReturn("lab")

        when(provider.findAll(RangeField)).thenReturn([rangeField_1, rangeField_2])

        RangeField rangeField = rangeField('label')

        assertThat(rangeField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find DateField by label")
    void findDateFieldByLabel() {
        DateField dateField_1 = mock(DateField)
        when(dateField_1.label()).thenReturn("label")
        DateField dateField_2 = mock(DateField)
        when(dateField_2.label()).thenReturn("lab")

        when(provider.findAll(DateField)).thenReturn([dateField_1, dateField_2])

        DateField dateField = dateField('label')

        assertThat(dateField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find ColorField by label")
    void findColorFieldByLabel() {
        ColorField colorField_1 = mock(ColorField)
        when(colorField_1.label()).thenReturn("label")
        ColorField colorField_2 = mock(ColorField)
        when(colorField_2.label()).thenReturn("lab")

        when(provider.findAll(ColorField)).thenReturn([colorField_1,colorField_2])

        ColorField colorField = colorField('label')

        assertThat(colorField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find DateTimeField by label")
    void findDateTimeFieldByLabel() {
        DateTimeField dateTimeField_1 = mock(DateTimeField)
        when(dateTimeField_1.label()).thenReturn("label")
        DateTimeField dateTimeField_2 = mock(DateTimeField)
        when(dateTimeField_2.label()).thenReturn("lab")

        when(provider.findAll(DateTimeField)).thenReturn([dateTimeField_1, dateTimeField_2])

        DateTimeField dateTimeField = dateTimeField('label')

        assertThat(dateTimeField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find MonthField by label")
    void findMonthFieldByLabel() {
        MonthField monthField_1 = mock(MonthField)
        when(monthField_1.label()).thenReturn("label")
        MonthField monthField_2 = mock(MonthField)
        when(monthField_2.label()).thenReturn("lab")

        when(provider.findAll(MonthField)).thenReturn([monthField_1, monthField_2])

        MonthField monthField = monthField('label')

        assertThat(monthField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find PhoneField by label")
    void findPhoneFieldByLabel() {
        PhoneField phoneField_1 = mock(PhoneField)
        when(phoneField_1.label()).thenReturn("label")
        PhoneField phoneField_2 = mock(PhoneField)
        when(phoneField_2.label()).thenReturn("lab")

        when(provider.findAll(PhoneField)).thenReturn([phoneField_1, phoneField_2])

        PhoneField phoneField = phoneField('label')

        assertThat(phoneField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find TimeField by label")
    void findTimeFieldByLabel() {
        TimeField timeField_1 = mock(TimeField)
        when(timeField_1.label()).thenReturn("label")
        TimeField timeField_2 = mock(TimeField)
        when(timeField_2.label()).thenReturn("lab")

        when(provider.findAll(TimeField)).thenReturn([timeField_1, timeField_2])

        TimeField timeField = timeField('label')

        assertThat(timeField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find WeekField by label")
    void findWeekFieldByLabel() {
        WeekField weekField_1 = mock(WeekField)
        when(weekField_1.label()).thenReturn("label")
        WeekField weekField_2 = mock(WeekField)
        when(weekField_2.label()).thenReturn("lab")

        when(provider.findAll(WeekField)).thenReturn([weekField_1, weekField_2])

        WeekField weekField = weekField('label')

        assertThat(weekField.label(), is('label'))
    }
}
