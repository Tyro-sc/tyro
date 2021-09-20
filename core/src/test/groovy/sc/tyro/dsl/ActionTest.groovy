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
import sc.tyro.core.*
import sc.tyro.core.component.*
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.TextField
import sc.tyro.core.input.MouseModifiers
import sc.tyro.core.support.Clearable

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Config.screenshotProvider
import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.*
import static sc.tyro.core.input.MouseModifiers.DOUBLE
import static sc.tyro.core.input.MouseModifiers.LEFT
import static sc.tyro.core.input.MouseModifiers.RIGHT
import static sc.tyro.core.input.MouseModifiers.SINGLE

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Test actions on components")
class ActionTest {
    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        screenshotProvider = spy(screenshotProvider)
    }

    @Test
    @DisplayName("Should visit")
    void visit() {
        verify(provider, times(0)).open('http://myUrl')

        visit('http://myUrl')

        verify(provider, times(1)).open('http://myUrl')
    }

    @Test
    @DisplayName("Should fill field")
    void fill() {
        TextField field = spy(TextField)

        fill field with 'Some value'

        verify(field, times(1)).value('Some value')
    }

    @Test
    @DisplayName("Should clear field")
    void clear() {
        Clearable clearable = spy(Clearable)

        clear clearable

        verify(clearable, times(1)).clear()
    }

    @Test
    @DisplayName("Should set")
    void set() {
        RangeField range = spy(RangeField)

        set range to 10

        verify(range, times(1)).value(10)
    }

    @Test
    @DisplayName("Should have on as placeholder")
    void on() {
        CheckBox checkBox = spy(CheckBox)

        assert on(checkBox).is(checkBox)
    }

    @Test
    @DisplayName("Should submit and reset a form")
    void submitAndReset() {
        Form form = spy(Form)

        Tyro.reset form // Explicit call to forbid Mockito reset method call
        verify(form, times(1)).reset()

        submit form
        verify(form, times(1)).submit()
    }

    @Test
    @DisplayName("Should check a checkable component")
    void check() {
        CheckBox checkBox_1 = mock(CheckBox)
        when(checkBox_1.enabled()).thenReturn(true)
        when(checkBox_1.checked()).thenReturn(false)

        CheckBox checkBox_2 = mock(CheckBox)
        when(checkBox_2.enabled()).thenReturn(true)
        when(checkBox_2.checked()).thenReturn(false)

        check checkBox_1, checkBox_2

        verify(checkBox_1, times(1)).click()
        verify(checkBox_2, times(1)).click()

        // Cannot check disabled component
        when(checkBox_1.enabled()).thenReturn(false)

        Exception ex = assertThrows(ComponentException, { check checkBox_1 })
        assertThat(ex.message, containsString('is disabled and cannot be checked'))

        // Cannot check already checked component
        when(checkBox_1.enabled()).thenReturn(true)
        when(checkBox_1.checked()).thenReturn(true)

        ex = assertThrows(ComponentException, { check checkBox_1 })
        assertThat(ex.message, containsString('is already checked and cannot be checked'))
    }

    @Test
    @DisplayName("Should uncheck a uncheckable component")
    void uncheck() {
        CheckBox checkBox_1 = mock(CheckBox)
        when(checkBox_1.enabled()).thenReturn(true)
        when(checkBox_1.checked()).thenReturn(true)

        CheckBox checkBox_2 = mock(CheckBox)
        when(checkBox_2.enabled()).thenReturn(true)
        when(checkBox_2.checked()).thenReturn(true)

        uncheck checkBox_1, checkBox_2

        verify(checkBox_1, times(1)).click()
        verify(checkBox_2, times(1)).click()

        // Cannot uncheck disabled component
        when(checkBox_1.enabled()).thenReturn(false)

        Exception ex = assertThrows(ComponentException, { uncheck checkBox_1 })
        assertThat(ex.message, containsString('is disabled and cannot be unchecked'))

        // Cannot uncheck already checked component
        when(checkBox_1.enabled()).thenReturn(true)
        when(checkBox_1.checked()).thenReturn(false)

        ex = assertThrows(ComponentException, { uncheck checkBox_1 })
        assertThat(ex.message, containsString('is already unchecked and cannot be unchecked'))
    }

    @Test
    @DisplayName("Should switch on a switchable component")
    void switchOn() {
        Switch switch_1 = mock(Switch)
        when(switch_1.enabled()).thenReturn(true)
        when(switch_1.on()).thenReturn(false)

        Switch switch_2 = mock(Switch)
        when(switch_2.enabled()).thenReturn(true)
        when(switch_2.on()).thenReturn(false)

        switchOn switch_1, switch_2

        verify(switch_1, times(1)).click()
        verify(switch_2, times(1)).click()

        // Cannot switch on disabled component
        when(switch_1.enabled()).thenReturn(false)

        Exception ex = assertThrows(ComponentException, { switchOn switch_1 })
        assertThat(ex.message, containsString('is disabled and cannot be switched on'))

        // Cannot switch on already switch on component
        when(switch_1.enabled()).thenReturn(true)
        when(switch_1.on()).thenReturn(true)

        ex = assertThrows(ComponentException, { switchOn switch_1 })
        assertThat(ex.message, containsString('is already switched on and cannot be switched on'))
    }

    @Test
    @DisplayName("Should switch off a un-switchable component")
    void switchOff() {
        Switch switch_1 = mock(Switch)
        when(switch_1.enabled()).thenReturn(true)
        when(switch_1.on()).thenReturn(true)

        Switch switch_2 = mock(Switch)
        when(switch_2.enabled()).thenReturn(true)
        when(switch_2.on()).thenReturn(true)

        switchOff switch_1, switch_2

        verify(switch_1, times(1)).click()
        verify(switch_2, times(1)).click()

        // Cannot switch off disabled component
        when(switch_1.enabled()).thenReturn(false)

        Exception ex = assertThrows(ComponentException, { switchOff switch_1 })
        assertThat(ex.message, containsString('is disabled and cannot be switched off'))

        // Cannot switch off already switch off component
        when(switch_1.enabled()).thenReturn(true)
        when(switch_1.on()).thenReturn(false)

        ex = assertThrows(ComponentException, { switchOff switch_1 })
        assertThat(ex.message, containsString('is already switched off and cannot be switched off'))
    }

    @Test
    @DisplayName("Should select an Item")
    void select() {
        Item item_1 = mock(Item)
        when(item_1.provider).thenReturn(provider)
        when(item_1.enabled()).thenReturn(true)

        Item item_2 = mock(Item)
        when(item_2.provider).thenReturn(provider)
        when(item_2.enabled()).thenReturn(true)

        select item_1, item_2

        verify(provider, times(1)).click(item_1, [LEFT, SINGLE], [CTRL])
        verify(provider, times(1)).click(item_2, [LEFT, SINGLE], [CTRL])

        // Cannot select disabled item
        when(item_1.enabled()).thenReturn(false)

        Exception ex = assertThrows(ComponentException, { select item_1 })
        assertThat(ex.message, containsString('is disabled and cannot be selected'))

        // Cannot select already selected item
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(true)

        ex = assertThrows(ComponentException, { select item_1 })
        assertThat(ex.message, containsString('is already selected and cannot be selected'))

        ex = assertThrows(ComponentException, { select item_1 })
        assertThat(ex.message, containsString('is already selected and cannot be selected'))
    }

    @Test
    @DisplayName("Should unselect an Item")
    void unselect() {
        Item item_1 = mock(Item)
        when(item_1.provider).thenReturn(provider)
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(true)

        Item item_2 = mock(Item)
        when(item_2.provider).thenReturn(provider)
        when(item_2.enabled()).thenReturn(true)
        when(item_2.selected()).thenReturn(true)

        unselect item_1, item_2

        verify(provider, times(1)).click(item_1, [LEFT, SINGLE], [CTRL])
        verify(provider, times(1)).click(item_2, [LEFT, SINGLE], [CTRL])

        // Cannot unselect disabled item
        when(item_1.enabled()).thenReturn(false)

        Exception ex = assertThrows(ComponentException, { unselect item_1 })
        assertThat(ex.message, containsString('is disabled and cannot be deselected'))

        // Cannot unselect already unselected item
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(false)

        ex = assertThrows(ComponentException, { unselect item_1 })
        assertThat(ex.message, containsString('is already unselected and cannot be deselected'))
    }

    @Test
    @DisplayName("Should select items in ListBox")
    void selectItemsInListBox() {
        ListBox listBox = mock(ListBox)

        Item item_1 = mock(Item)
        when(item_1.provider).thenReturn(provider)
        when(item_1.enabled()).thenReturn(true)
        when(item_1.value()).thenReturn("Item_1")

        Item item_2 = mock(Item)
        when(item_2.provider).thenReturn(provider)
        when(item_2.enabled()).thenReturn(true)
        when(item_2.value()).thenReturn("Item_2")

        Item item_3 = mock(Item)
        when(item_3.provider).thenReturn(provider)

        when(listBox.items()).thenReturn([item_1, item_2])
        when(listBox.contains(item_1)).thenReturn(true)
        when(listBox.contains(item_2)).thenReturn(true)

        listBox.select(item_1, item_2)
        verify(provider, times(1)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(1)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        listBox.select("Item_1", "Item_2")
        verify(provider, times(2)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(2)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        Exception ex = assertThrows(ComponentException, { listBox.select(item_3) })
        assertThat(ex.message, containsString('is not contains by'))
    }

    @Test
    @DisplayName("Should unselect items in ListBox")
    void unselectItemsInListBox() {
        ListBox listBox = mock(ListBox)

        Item item_1 = mock(Item)
        when(item_1.provider).thenReturn(provider)
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(true)
        when(item_1.value()).thenReturn("Item_1")

        Item item_2 = mock(Item)
        when(item_2.provider).thenReturn(provider)
        when(item_2.enabled()).thenReturn(true)
        when(item_2.selected()).thenReturn(true)
        when(item_2.value()).thenReturn("Item_2")

        Item item_3 = mock(Item)
        when(item_3.provider).thenReturn(provider)

        when(listBox.items()).thenReturn([item_1, item_2])
        when(listBox.contains(item_1)).thenReturn(true)
        when(listBox.contains(item_2)).thenReturn(true)

        listBox.unselect(item_1, item_2)
        verify(provider, times(1)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(1)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        listBox.unselect("Item_1", "Item_2")
        verify(provider, times(2)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(2)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        Exception ex = assertThrows(ComponentException, { listBox.unselect(item_3) })
        assertThat(ex.message, containsString('is not contains by'))
    }

    @Test
    @DisplayName("Should delegate to mouse")
    void mouseDelegation() {
        Config.provider = mock(Provider)
        Config.meta = mock(MetaDataProvider)
        Component cmp_1 = new Component()
        Component cmp_2 = new Component()
        when(Config.meta.metaInfo(any(Component))).thenReturn(new MetaInfo(id: '1', node: 'node'))

        clickOn cmp_1
        verify(provider, times(1)).click(cmp_1, [LEFT, SINGLE], [])

        doubleClickOn cmp_1
        verify(provider, times(1)).click(cmp_1, [LEFT, DOUBLE], [])

        rightClickOn cmp_1
        verify(provider, times(1)).click(cmp_1, [RIGHT, SINGLE], [])

        hoveringMouseOn cmp_1
        verify(provider, times(1)).mouseOver(cmp_1)

        drag cmp_1 on cmp_2
        verify(provider, times(1)).dragAndDrop(cmp_1, cmp_2)
    }

    @Test
    @DisplayName("Should delegate to Keyboard")
    void keyboardDelegation() {
        type 'Some input'
        verify(provider, times(1)).type(['Some input'])

        type CTRL
        verify(provider, times(1)).type([CTRL])

        type CTRL + SHIFT + DELETE
        verify(provider, times(1)).type([CTRL, SHIFT, DELETE])

        type CTRL + 'c'
        verify(provider, times(1)).type([CTRL, 'c'])
    }

    @Test
    @DisplayName("Should take a window screenshot")
    void windowScreenshot() {
        verify(screenshotProvider, times(0)).takeScreenshot('window', null)

        takeScreenshot('window')

        verify(screenshotProvider, times(1)).takeScreenshot('window', null)
    }

    @Test
    @DisplayName("Should take a component screenshot")
    void componentScreenshot() {
        Component cmp = new Component()
        verify(screenshotProvider, times(0)).takeScreenshot('component', cmp)

        takeScreenshot('component', cmp)

        verify(screenshotProvider, times(1)).takeScreenshot('component', cmp)
    }
}