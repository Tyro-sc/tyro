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
import sc.tyro.core.component.field.EmailField
import sc.tyro.core.component.field.Field
import sc.tyro.core.component.field.PasswordField

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
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

    // =================  By Label / Placeholder  ======================

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
    void findCheckBoxByLabel() {
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
    @DisplayName("Should find field by label or placeholder")
    void findFieldByLabel() {
        PasswordField password = mock(PasswordField)
        when(password.label()).thenReturn('Label')
        EmailField email = mock(EmailField)
        when(email.placeholder()).thenReturn('!Label')

        when(provider.findAll(Field)).thenReturn(List.of(password, email))

        field('Label').should { be available }

        // Should fail if more than on match
        when(email.placeholder()).thenReturn('Label')

        IllegalStateException error = assertThrows(IllegalStateException, { field("Label").should { be available } })
        assertThat(error.message, is("Find 2 component(s) Field with label or placeholder 'Label'."))
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
