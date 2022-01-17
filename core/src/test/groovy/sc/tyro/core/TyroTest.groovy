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
package sc.tyro.core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.*
import sc.tyro.core.component.field.Field

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.startsWith
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.ArgumentMatchers.any
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
        Config.meta = mock(MetaDataProvider)

        Button button_1 = spy(Button)
        doReturn('id1').when(button_1).id()
        doReturn('Ok').when(button_1).text()
        doReturn(true).when(button_1).available()
        Button button_2 = spy(Button)
        doReturn('id2').when(button_2).id()
        doReturn('!Ok').when(button_2).text()
        doReturn(true).when(button_2).available()

        when(provider.findAll(Button)).thenReturn(List.of(button_1, button_2))

        button('Ok').should { be available }

        // Should fail if more than on match
        when(button_2.text()).thenReturn('Ok')
        ComponentException error = assertThrows(ComponentException, { button('Ok').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Button with text 'Ok'"))

        Button unavailable = spy(Button)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        button('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find heading by text")
    void findHeadingByText() {
        Heading heading_1 = spy(Heading)
        doReturn('id1').when(heading_1).id()
        doReturn('Title').when(heading_1).text()
        doReturn(true).when(heading_1).available()
        Heading heading_2 = mock(Heading)
        doReturn('id2').when(heading_1).id()
        doReturn('!Title').when(heading_2).text()
        doReturn(true).when(heading_2).available()

        when(provider.findAll(Heading)).thenReturn(List.of(heading_1, heading_2))

        heading('Title').should { be available }

        // Should fail if more than on match
        when(heading_2.text()).thenReturn('Title')

        ComponentException error = assertThrows(ComponentException, { heading('Title').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Heading with text 'Title'"))

        Heading unavailable = spy(Heading)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        heading('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find link by text")
    void findLinkByText() {
        Link link_1 = spy(Link)
        doReturn('id1').when(link_1).id()
        doReturn('Link').when(link_1).text()
        doReturn(true).when(link_1).available()
        Link link_2 = spy(Link)
        doReturn('id2').when(link_2).id()
        doReturn('!Link').when(link_2).text()
        doReturn(true).when(link_2).available()

        when(provider.findAll(Link)).thenReturn(List.of(link_1, link_2))

        link('Link').should { be available }

        // Should fail if more than on match
        when(link_2.text()).thenReturn('Link')

        ComponentException error = assertThrows(ComponentException, { link('Link').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Link with text 'Link'"))

        Link unavailable = spy(Link)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        link('Unavailable').should { be missing }
    }

    // =================  By Label / Placeholder  ======================

    @Test
    @DisplayName("Should find radio by label")
    void findRadioByLabel() {
        Radio radio_1 = spy(Radio)
        doReturn('id1').when(radio_1).id()
        doReturn('Label').when(radio_1).label()
        doReturn(true).when(radio_1).available()
        Radio radio_2 = spy(Radio)
        doReturn('id2').when(radio_2).id()
        doReturn('!Label').when(radio_2).label()
        doReturn(true).when(radio_2).available()

        when(provider.findAll(Radio)).thenReturn(List.of(radio_1, radio_2))

        radio('Label').should { be available }

        // Should fail if more than on match
        when(radio_2.label()).thenReturn('Label')

        ComponentException error = assertThrows(ComponentException, { radio('Label').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Radio with label 'Label'"))

        Radio unavailable = spy(Radio)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        radio('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find checkbox by label")
    void findCheckBoxByLabel() {
        CheckBox checkBox_1 = spy(CheckBox)
        doReturn('id1').when(checkBox_1).id()
        doReturn('Label').when(checkBox_1).label()
        doReturn(true).when(checkBox_1).available()
        CheckBox checkBox_2 = spy(CheckBox)
        doReturn('id2').when(checkBox_2).id()
        doReturn('!Label').when(checkBox_2).label()
        doReturn(true).when(checkBox_2).available()

        when(provider.findAll(CheckBox)).thenReturn(List.of(checkBox_1, checkBox_2))

        checkbox('Label').should { be available }

        // Should fail if more than on match
        when(checkBox_2.label()).thenReturn('Label')

        ComponentException error = assertThrows(ComponentException, { checkbox('Label').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.CheckBox with label 'Label'"))

        CheckBox unavailable = spy(CheckBox)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        checkbox('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find listBox by label")
    void findListBoxByLabel() {
        ListBox listBox_1 = spy(ListBox)
        doReturn('id1').when(listBox_1).id()
        doReturn('Label').when(listBox_1).label()
        doReturn(true).when(listBox_1).available()
        ListBox listBox_2 = spy(ListBox)
        doReturn('id2').when(listBox_2).id()
        doReturn('!Label').when(listBox_2).label()
        doReturn(true).when(listBox_2).available()

        when(provider.findAll(ListBox)).thenReturn(List.of(listBox_1, listBox_2))

        listBox('Label').should { be available }

        // Should fail if more than on match
        when(listBox_2.label()).thenReturn('Label')

        ComponentException error = assertThrows(ComponentException, { listBox('Label').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.ListBox with label 'Label'"))

        ListBox unavailable = spy(ListBox)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        listBox('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find dropdown by label")
    void findDropdownByLabel() {
        Dropdown dropdown_1 = spy(Dropdown)
        doReturn('id1').when(dropdown_1).id()
        doReturn('Label').when(dropdown_1).label()
        doReturn(true).when(dropdown_1).available()
        Dropdown dropdown_2 = spy(Dropdown)
        doReturn('id2').when(dropdown_2).id()
        doReturn('!Label').when(dropdown_2).label()
        doReturn(true).when(dropdown_2).available()

        when(provider.findAll(Dropdown)).thenReturn(List.of(dropdown_1, dropdown_2))

        dropdown('Label').should { be available }

        // Should fail if more than on match
        when(dropdown_2.label()).thenReturn('Label')

        ComponentException error = assertThrows(ComponentException, { dropdown("Label").should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Dropdown with label 'Label'"))

        Dropdown unavailable = spy(Dropdown)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        dropdown('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find field by label or placeholder")
    void findFieldByLabel() {
        Field field_1 = spy(Field)
        doReturn('id1').when(field_1).id()
        doReturn('Label').when(field_1).label()
        doReturn(true).when(field_1).available()
        Field field_2 = spy(Field)
        doReturn('id2').when(field_2).id()
        doReturn('!Label').when(field_2).placeholder()
        doReturn(true).when(field_2).available()

        when(provider.findAll(Field)).thenReturn(List.of(field_1, field_2))

        field('Label').should { be available }
        field('!Label').should { be available }

        // Should fail if more than on match
        when(field_2.placeholder()).thenReturn('Label')

        ComponentException error = assertThrows(ComponentException, { field('Label').should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.field.Field with label or placeholder 'Label'"))

        Field unavailable = spy(Field)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        field('Unavailable').should { be missing }
    }

    // =================  By Value  ======================

    @Test
    @DisplayName("Should find group by value")
    void findGroupByValue() {
        Group group_1 = mock(Group)
        doReturn('Value').when(group_1).value()
        doReturn(true).when(group_1).available()
        Group group_2 = mock(Group)
        doReturn('!Value').when(group_2).value()
        doReturn(true).when(group_2).available()

        when(provider.findAll(Group)).thenReturn(List.of(group_1, group_2))

        group("Value").should { be available }

        // Should fail if more than on match
        when(group_2.value()).thenReturn('Value')

        ComponentException error = assertThrows(ComponentException, { group("Value").should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Group with value 'Value'"))

        Group unavailable = spy(Group)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        group('Unavailable').should { be missing }
    }

    @Test
    @DisplayName("Should find item by value")
    void findItemByValue() {
        Item item_1 = mock(Item)
        doReturn('Value').when(item_1).value()
        doReturn(true).when(item_1).available()
        Item item_2 = mock(Item)
        doReturn('!Value').when(item_2).value()
        doReturn(true).when(item_2).available()

        when(provider.findAll(Item)).thenReturn(List.of(item_1, item_2))

        item("Value").should { be available }

        // Should fail if more than on match
        when(item_2.value()).thenReturn('Value')

        ComponentException error = assertThrows(ComponentException, { item("Value").should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Item with value 'Value'"))

        Item unavailable = spy(Item)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        item('Unavailable').should { be missing }
    }

    // =================  By Title  ======================

    @Test
    @DisplayName("Should find panel by title")
    void findPanelByTitle() {
        Panel panel_1 = spy(Panel)
        doReturn('id1').when(panel_1).id()
        doReturn('Title').when(panel_1).title()
        doReturn(true).when(panel_1).available()
        Panel panel_2 = spy(Panel)
        doReturn('id2').when(panel_2).id()
        doReturn('!Title').when(panel_2).title()
        doReturn(true).when(panel_2).available()

        when(provider.findAll(Panel)).thenReturn(List.of(panel_1, panel_2))

        panel("Title").should { be available }

        // Should fail if more than on match
        when(panel_2.title()).thenReturn('Title')

        ComponentException error = assertThrows(ComponentException, { panel("Title").should { be available } })
        assertThat(error.message, startsWith("Find 2 Component(s) sc.tyro.core.component.Panel with title 'Title'"))

        Panel unavailable = spy(Panel)
        doReturn(false).when(unavailable).available()
        when(provider.find(any(), any(By))).thenReturn(unavailable)

        // Should provide a none available component
        panel("Unavailable").should { be missing }
    }
}
