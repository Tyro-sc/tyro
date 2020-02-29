package sc.tyro.dsl

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.Tyro
import sc.tyro.core.component.CheckBox
import sc.tyro.core.component.Form
import sc.tyro.core.component.Item
import sc.tyro.core.component.ListBox
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.TextField

import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.CTRL
import static sc.tyro.core.input.MouseModifiers.LEFT
import static sc.tyro.core.input.MouseModifiers.SINGLE

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Test actions on components")
class ActionTest {
    @Test
    @DisplayName("Should visit")
    void should_visit() {
        provider = mock(Provider)

        verify(provider, times(0)).open('http://myUrl')

        visit('http://myUrl')

        verify(provider, times(1)).open('http://myUrl')
    }

    @Test
    @DisplayName("Should type text")
    void should_type_text() {
        provider = mock(Provider)

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
    @Disabled
    void should_be_able_to_select_items_in_components_containing_items() {
        provider = mock(Provider)

        ListBox listBox = spy(ListBox)
        Item item_1 = spy(Item)
        Item item_2 = spy(Item)

        doReturn([item_1, item_2]).when(listBox).items()
        when(provider.enabled(item_1)).thenReturn(true)
        when(provider.metaInfo(item_1)).thenReturn(new MetaInfo('node', '1'))
        when(provider.enabled(item_2)).thenReturn(true)
        when(provider.metaInfo(item_2)).thenReturn(new MetaInfo('node', '2'))

        listBox.select(item_1)

        verify(provider, times(1)).click(item_1, [LEFT, SINGLE], [CTRL])
        verify(provider, times(0)).click(item_2, [LEFT, SINGLE], [CTRL])
//
//        reset(org.testatoo.core.Testatoo.getConfig.evaluator)
//        reset(item_1)
//        reset(item_2)
//        Mockito.doReturn('1').when(item_1).id()
//        Mockito.doReturn('2').when(item_2).id()
//        Mockito.doReturn(true).when(item_1).selected()
//
//        listBox.unselect(item_1)
//        Mockito.verify(org.testatoo.core.Testatoo.getConfig.evaluator, Mockito.times(1)).click('1', [org.testatoo.core.input.MouseModifiers.LEFT, org.testatoo.core.input.MouseModifiers.SINGLE], [org.testatoo.core.input.Key.CTRL])
//        Mockito.verify(org.testatoo.core.Testatoo.getConfig.evaluator, Mockito.times(0)).click('2', [org.testatoo.core.input.MouseModifiers.LEFT, org.testatoo.core.input.MouseModifiers.SINGLE], [org.testatoo.core.input.Key.CTRL])
//
//        reset(org.testatoo.core.Testatoo.getConfig.evaluator)
//        reset(item_1)
//        reset(item_2)
//        Mockito.doReturn('1').when(item_1).id()
//        Mockito.doReturn('2').when(item_2).id()
//        Mockito.doReturn('Item_1').when(item_1).value()
//        Mockito.doReturn('Item_2').when(item_2).value()
//
//        listBox.select('Item_2')
//        Mockito.verify(org.testatoo.core.Testatoo.getConfig.evaluator, Mockito.times(0)).click('1', [org.testatoo.core.input.MouseModifiers.LEFT, org.testatoo.core.input.MouseModifiers.SINGLE], [org.testatoo.core.input.Key.CTRL])
//        Mockito.verify(org.testatoo.core.Testatoo.getConfig.evaluator, Mockito.times(1)).click('2', [org.testatoo.core.input.MouseModifiers.LEFT, org.testatoo.core.input.MouseModifiers.SINGLE], [org.testatoo.core.input.Key.CTRL])
//
//        reset(org.testatoo.core.Testatoo.getConfig.evaluator)
//        reset(item_1)
//        reset(item_2)
//        Mockito.doReturn('1').when(item_1).id()
//        Mockito.doReturn('2').when(item_2).id()
//        Mockito.doReturn('Item_1').when(item_1).value()
//        Mockito.doReturn('Item_2').when(item_2).value()
//        Mockito.doReturn(true).when(item_1).selected()
//        Mockito.doReturn(true).when(item_2).selected()
//
//        listBox.unselect('Item_1', 'Item_2')
//        Mockito.verify(org.testatoo.core.Testatoo.getConfig.evaluator, Mockito.times(1)).click('1', [org.testatoo.core.input.MouseModifiers.LEFT, org.testatoo.core.input.MouseModifiers.SINGLE], [org.testatoo.core.input.Key.CTRL])
//        Mockito.verify(org.testatoo.core.Testatoo.getConfig.evaluator, Mockito.times(1)).click('2', [org.testatoo.core.input.MouseModifiers.LEFT, org.testatoo.core.input.MouseModifiers.SINGLE], [org.testatoo.core.input.Key.CTRL])
    }
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
}
