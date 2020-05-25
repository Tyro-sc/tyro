package sc.tyro.dsl

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.Provider
import sc.tyro.core.component.CheckBox
import sc.tyro.core.component.Component
import sc.tyro.core.component.Item
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.TextField
import sc.tyro.core.support.state.CollapseSupport

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.*
import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("States")
class StateTest {
    @Test
    @DisplayName("Should support Component default states: available, enabled, visible, missing, disabled and hidden")
    void should_support_available_enabled_visible() {
        Component cmp = spy(new Component())

        doReturn(true).when(cmp).available()
        doReturn(true).when(cmp).enabled()
        doReturn(true).when(cmp).visible()

        cmp.should {
            be available
            be enabled
            be visible
        }

        doReturn(false).when(cmp).available()
        doReturn(false).when(cmp).enabled()
        doReturn(false).when(cmp).visible()

        cmp.should {
            be missing
            be disabled
            be hidden
        }
    }

    @Test
    @DisplayName("Should support checked and unchecked")
    void should_support_checked_unchecked() {
        CheckBox checkbox = spy(CheckBox)
        doReturn(true).when(checkbox).enabled()
        doReturn(false).when(checkbox).checked()

        checkbox.should { be unchecked }

        doReturn(true).when(checkbox).checked()

        checkbox.should { be checked }
    }

    @Test
    @DisplayName("Should support required and optional")
    void should_support_required() {
        TextField field = spy(TextField)
        doReturn(false).when(field).required()

        field.should { be optional }

        doReturn(true).when(field).required()

        field.should { be required }
    }

    @Test
    @DisplayName("Should support valid and invalid")
    void should_support_valid() {
        TextField field = spy(TextField)
        doReturn(true).when(field).valid()

        field.should { be valid }

        doReturn(false).when(field).valid()

        field.should { be invalid }
    }

    @Test
    @DisplayName("Should support empty and filled")
    void should_support_empty() {
        TextField field = spy(TextField)
        doReturn(true).when(field).empty()

        field.should { be empty }

        doReturn(false).when(field).empty()

        field.should { be filled }
    }

    @Test
    @DisplayName("Should support read only")
    void should_support_readOnly() {
        TextField field = spy(TextField)
        doReturn(true).when(field).readOnly()

        field.should { be readOnly }

        doReturn(false).when(field).readOnly()

        assertThrows(AssertionError, { field.should { be readOnly } })
    }

    @Test
    @DisplayName("Should support focused")
    void should_support_focused() {
        TextField field = spy(TextField)
        doReturn(true).when(field).focused()

        field.should { be focused }

        doReturn(false).when(field).focused()

        assertThrows(AssertionError, { field.should { be focused } })
    }

    @Test
    @DisplayName("Should support expanded")
    void should_support_expanded() {
        Widget widget = mock(Widget.class,
                withSettings()
                        .useConstructor()
                        .outerInstance(this)
                        .defaultAnswer(CALLS_REAL_METHODS))
        doReturn(true).when(widget).expanded()

        widget.should { be expanded }

        doReturn(false).when(widget).expanded()

        assertThrows(AssertionError, { widget.should { be expanded } })
    }

    @Test
    @DisplayName("Should support collapsed")
    void should_support_collapsed() {
        Widget widget = mock(Widget.class,
                withSettings()
                        .useConstructor()
                        .outerInstance(this)
                        .defaultAnswer(CALLS_REAL_METHODS))
        doReturn(true).when(widget).collapsed()

        widget.should { be collapsed }

        doReturn(false).when(widget).collapsed()

        assertThrows(AssertionError, { widget.should { be collapsed } })
    }

    @Test
    @DisplayName("Should support selected ans unselected")
    void should_support_selected() {
        Item item = spy(Item)
        doReturn(false).when(item).selected()

        item.should { be unselected }

        doReturn(true).when(item).selected()

        item.should { be selected }
    }

    @Test
    @DisplayName("Should support range")
    void should_support_range() {
        RangeField field = spy(RangeField)
        doReturn(true).when(field).inRange()

        field.should { be inRange }

        doReturn(false).when(field).inRange()

        field.should { be outOfRange }
    }

    @Test
    @DisplayName("Should support contains")
    void should_support_contain() {
        Provider provider = mock(Provider)

        Component cmp_1 = spy(new Component(provider, null))
        doReturn('1').when(cmp_1).id()
        Component cmp_2 = spy(new Component(provider, null))
        doReturn('2').when(cmp_2).id()
        Component cmp_3 = spy(new Component(provider, null))
        doReturn('3').when(cmp_3).id()

        when(cmp_1.provider.contains(cmp_2)).thenReturn(true)
        when(cmp_1.provider.contains(cmp_3)).thenReturn(false)

        cmp_1.should { contain cmp_2 }

        assertThrows(AssertionError, { cmp_1.should { contain cmp_2, cmp_3 } })
    }

    private abstract class Widget extends Component implements CollapseSupport {}
}
