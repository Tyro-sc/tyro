package sc.tyro.dsl

import org.junit.jupiter.api.*
import sc.tyro.core.ComponentException
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.Tyro
import sc.tyro.core.component.*
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.TextField
import sc.tyro.core.input.Keyboard
import sc.tyro.core.input.Mouse
import sc.tyro.core.input.MouseModifiers
import sc.tyro.core.support.Clearable

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.*
import static sc.tyro.core.input.MouseModifiers.LEFT
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
    }

    @AfterEach()
    void tearDown() {
        reset(provider)
    }

    @Test
    @DisplayName("Should visit")
    void should_visit() {
        verify(provider, times(0)).open('http://myUrl')

        visit('http://myUrl')

        verify(provider, times(1)).open('http://myUrl')
    }

    @Test
    @DisplayName("Should type text")
    void should_type_text() {
        verify(provider, times(0)).type(['data'])

        type('data')

        verify(provider, times(1)).type(['data'])
    }

    @Test
    @DisplayName("Should fill field")
    void should_fill_field() {
        TextField field = spy(TextField)

        fill field with 'Some value'

        verify(field, times(1)).value('Some value')
    }

    @Test
    @DisplayName("Should clear field")
    void should_clear_field() {
        Clearable clearable = spy(Clearable)

        clear clearable

        verify(clearable, times(1)).clear()
    }

    @Test
    @DisplayName("Should set")
    void should_be_set() {
        RangeField range = spy(RangeField)

        set range to 10

        verify(range, times(1)).value(10)
    }

    @Test
    @DisplayName("Should have on as placeholder")
    void should_have_on_as_placeholder() {
        CheckBox checkBox = spy(CheckBox)

        assert on(checkBox).is(checkBox)
    }

    @Test
    @DisplayName("Should submit and reset a form")
    void should_be_able_to_submit_and_reset_form() {
        Form form = spy(Form)

        Tyro.reset form // Explicit call to forbid Mockito reset method call

        verify(form, times(1)).reset()

        submit form

        verify(form, times(1)).submit()
    }

    @Test
    @DisplayName("Should check a checkable component")
    void should_check() {
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

        ComponentException error = assertThrows(ComponentException, {
            check checkBox_1
        }) as ComponentException

        assertThat(error.message, containsString('is disabled and cannot be checked'))

        // Cannot check already checked component
        when(checkBox_1.enabled()).thenReturn(true)
        when(checkBox_1.checked()).thenReturn(true)

        error = assertThrows(ComponentException, {
            check checkBox_1
        }) as ComponentException

        assertThat(error.message, containsString('is already checked and cannot be checked'))
    }

    @Test
    @DisplayName("Should uncheck a uncheckable component")
    void should_uncheck() {
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

        ComponentException error = assertThrows(ComponentException, {
            uncheck checkBox_1
        }) as ComponentException

        assertThat(error.message, containsString('is disabled and cannot be unchecked'))

        // Cannot uncheck already checked component
        when(checkBox_1.enabled()).thenReturn(true)
        when(checkBox_1.checked()).thenReturn(false)

        error = assertThrows(ComponentException, {
            uncheck checkBox_1
        }) as ComponentException

        assertThat(error.message, containsString('is already unchecked and cannot be unchecked'))
    }

    @Test
    @DisplayName("Should select an Item")
    void should_select() {
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

        ComponentException error = assertThrows(ComponentException, {
            select item_1
        }) as ComponentException

        assertThat(error.message, containsString('is disabled and cannot be selected'))

        // Cannot select already selected item
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(true)

        error = assertThrows(ComponentException, {
            select item_1
        }) as ComponentException

        assertThat(error.message, containsString('is already selected and cannot be selected'))
    }

    @Test
    @DisplayName("Should unselect an Item")
    void should_unselect() {
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

        ComponentException error = assertThrows(ComponentException, {
            unselect item_1
        }) as ComponentException

        assertThat(error.message, containsString('is disabled and cannot be deselected'))

        // Cannot unselect already unselected item
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(false)

        error = assertThrows(ComponentException, {
            unselect item_1
        }) as ComponentException

        assertThat(error.message, containsString('is already unselected and cannot be deselected'))
    }

    @Test
    @DisplayName("Should select items in Listbox")
    void should_be_able_to_select_items_in_listbox() {
        ListBox listBox = mock(ListBox)

        Item item_1 = mock(Item)
        when(item_1.provider).thenReturn(provider)
        when(item_1.enabled()).thenReturn(true)
        when(item_1.provider.metaInfo(item_1)).thenReturn(new MetaInfo('node', '1'))
        when(item_1.value()).thenReturn("Item_1")

        Item item_2 = mock(Item)
        when(item_2.provider).thenReturn(provider)
        when(item_2.enabled()).thenReturn(true)
        when(item_2.provider.metaInfo(item_2)).thenReturn(new MetaInfo('node', '2'))
        when(item_2.value()).thenReturn("Item_2")

        Item item_3 = mock(Item)
        when(item_3.provider).thenReturn(provider)
        when(item_3.provider.metaInfo(item_3)).thenReturn(new MetaInfo('node', '3'))

        when(listBox.items()).thenReturn([item_1, item_2])
        when(listBox.contains(item_1)).thenReturn(true)
        when(listBox.contains(item_2)).thenReturn(true)

        listBox.select(item_1, item_2)
        verify(provider, times(1)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(1)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        listBox.select("Item_1", "Item_2")
        verify(provider, times(2)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(2)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        ComponentException error = assertThrows(ComponentException, {
            listBox.select(item_3)
        }) as ComponentException

        assertThat(error.message, containsString('is not contains by'))
    }

    @Test
    @DisplayName("Should unselect items in Listbox")
    void should_be_able_to_unselect_items_in_listbox() {
        ListBox listBox = mock(ListBox)

        Item item_1 = mock(Item)
        when(item_1.provider).thenReturn(provider)
        when(item_1.enabled()).thenReturn(true)
        when(item_1.selected()).thenReturn(true)
        when(item_1.provider.metaInfo(item_1)).thenReturn(new MetaInfo('node', '1'))
        when(item_1.value()).thenReturn("Item_1")

        Item item_2 = mock(Item)
        when(item_2.provider).thenReturn(provider)
        when(item_2.enabled()).thenReturn(true)
        when(item_2.selected()).thenReturn(true)
        when(item_2.provider.metaInfo(item_2)).thenReturn(new MetaInfo('node', '2'))
        when(item_2.value()).thenReturn("Item_2")

        Item item_3 = mock(Item)
        when(item_3.provider).thenReturn(provider)
        when(item_3.provider.metaInfo(item_3)).thenReturn(new MetaInfo('node', '3'))

        when(listBox.items()).thenReturn([item_1, item_2])
        when(listBox.contains(item_1)).thenReturn(true)
        when(listBox.contains(item_2)).thenReturn(true)

        listBox.unselect(item_1, item_2)
        verify(provider, times(1)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(1)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        listBox.unselect("Item_1", "Item_2")
        verify(provider, times(2)).click(item_1, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])
        verify(provider, times(2)).click(item_2, [LEFT, SINGLE] as Collection<MouseModifiers>, [CTRL])

        ComponentException error = assertThrows(ComponentException, {
            listBox.unselect(item_3)
        }) as ComponentException

        assertThat(error.message, containsString('is not contains by'))
    }

    @Test
    @DisplayName("Should delegate to mouse")
    void mouse_delegation() {
        Mouse mouse = mock(Mouse)
        Tyro.mouse = mouse

        Component component = mock(Component)

        clickOn component
        verify(mouse, times(1)).clickOn(component)

        doubleClickOn component
        verify(mouse, times(1)).doubleClickOn(component)

        rightClickOn component
        verify(mouse, times(1)).rightClickOn(component)

        hoveringMouseOn component
        verify(mouse, times(1)).hoveringMouseOn(component)

        drag component
        verify(mouse, times(1)).drag(component)
    }

    @Test
    @DisplayName("Should delegate to Keyboard")
    void keyboard_delegation() {
        Keyboard keyboard = mock(Keyboard)
        Tyro.keyboard = keyboard

        type 'Some input'
        verify(keyboard, times(1)).type(['Some input'])

        type CTRL
        verify(keyboard, times(1)).type([CTRL])

        type CTRL + SHIFT + DELETE
        verify(keyboard, times(1)).type([CTRL, SHIFT, DELETE])

        type CTRL + 'c'
        verify(keyboard, times(1)).type([CTRL, 'c'])
    }

    @Test
    @DisplayName("Should delegate to Factory")
    void factory_delegation() {
//        ComponentFactory factory = mock(ComponentFactory)
//
//        button("Ok")
//        verify(factory, times(1)).collectAll(Button)
    }
}
//    =========================================================================================================
//    TODO


//
//    @Test
//    void should_throw_an_error_when_action_on_component_does_not_correspond_to_its_state() {
//        CheckBox checkbox = spy(new CheckBoxStub())
//        checkbox.meta = meta
//
//        Mockito.doReturn(false).when(checkbox).enabled()
//        try {
//            org.testatoo.core.Testatoo.check checkbox
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is disabled and cannot be checked')
//        }
//
//        Mockito.doReturn(true).when(checkbox).enabled()
//        Mockito.doReturn(true).when(checkbox).checked()
//        try {
//            org.testatoo.core.Testatoo.check checkbox
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is already checked and cannot be checked')
//        }
//
//        Mockito.doReturn(false).when(checkbox).enabled()
//        try {
//            org.testatoo.core.Testatoo.uncheck checkbox
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is disabled and cannot be unchecked')
//        }
//
//        Mockito.doReturn(true).when(checkbox).enabled()
//        Mockito.doReturn(false).when(checkbox).checked()
//        try {
//            org.testatoo.core.Testatoo.uncheck checkbox
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is already unchecked and cannot be unchecked')
//        }
//
//        ListBox listBox = spy(new ListBoxStub())
//        listBox.meta = meta
//
//        Item item_1 = spy(new ItemStub())
//        item_1.meta = meta
//
//        Mockito.doReturn([item_1]).when(listBox).items()
//        Mockito.doReturn(false).when(item_1).enabled()
//
//        try {
//            listBox.select(item_1)
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is disabled and cannot be selected')
//        }
//
//        reset(item_1)
//        Mockito.doReturn(true).when(item_1).enabled()
//        Mockito.doReturn(true).when(item_1).selected()
//
//        try {
//            listBox.select(item_1)
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is already selected and cannot be selected')
//        }
//
//        reset(item_1)
//        Mockito.doReturn(false).when(item_1).enabled()
//        try {
//            listBox.unselect(item_1)
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is disabled and cannot be deselected')
//        }
//
//        reset(item_1)
//        Mockito.doReturn(true).when(item_1).enabled()
//        Mockito.doReturn(false).when(item_1).selected()
//
//        try {
//            listBox.unselect(item_1)
//            org.junit.Assert.fail()
//        } catch (ComponentException e) {
//            assert e.message.endsWith('is already unselected and cannot be deselected')
//        }
//    }
//}
