package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.component.Panel
import sc.tyro.web.CssIdentifier
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Mouse Tests")
class MouseTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/mouse.html'
    }

    @Test
    @DisplayName("Should click")
    void click() {
        Button button = $('#button_1') as Button
        assert button.text() == 'Button'
        clickOn button
        assert button.text() == 'Button Clicked!'

        browser().refresh()

        button = $('#button_1') as Button
        assert button.text() == 'Button'
        button.click()
        assert button.text() == 'Button Clicked!'
    }

    @Test
    @DisplayName("Should double click")
    void doubleClick() {
        Button button = $('#button_2') as Button

        assert button.text() == 'Button'
        doubleClickOn button
        assert button.text() == 'Button Double Clicked!'

        browser().refresh()

        button = $('#button_2') as Button

        assert button.text() == 'Button'
        button.doubleClick()
        assert button.text() == 'Button Double Clicked!'
    }

    @Test
    @DisplayName("Should right click")
    void rightClick() {
        Button button = $('#button_5') as Button

        assert button.text() == 'Button'
        rightClickOn button
        assert button.text() == 'Button Right Clicked!'

        browser().refresh()

        button = $('#button_5') as Button

        assert button.text() == 'Button'
        button.rightClick()
        assert button.text() == 'Button Right Clicked!'
    }

    @Test
    @DisplayName("Should mouse over")
    void mouseOver() {
        Button button = $('#button_3') as Button
        assert button.text() == 'Button'

        hoveringMouseOn button

        assert button.text() == 'Button Mouse Over!'
    }

    @Test
    @DisplayName("Should mouse out")
    void mouseOut() {
        Button button = $('#button_4') as Button
        assert button.text() == 'Button'

        // To simulate mouse out
        // 1 - mouse over the component
        hoveringMouseOn button
        // 2 - mouse over an another component
        hoveringMouseOn $('#button_5') as Button
        // The mouse out is triggered
        assert button.text() == 'Button Mouse Out!'
    }

    @Test
    @DisplayName("Should drag and drop")
    void dragAndDrop() {
        DropPanel dropPanel = $('#drop-zone') as DropPanel
        assert dropPanel.title() == 'Drop here'

        Div dragPanel = $('#drag-drop') as Div
        drag dragPanel on dropPanel
        assert dropPanel.title() == 'Dropped!'

        browser().refresh()

        dropPanel = $('#drop-zone') as DropPanel
        assert dropPanel.title() == 'Drop here'

        dragPanel = $('#drag-drop') as Div
        dragPanel.drag().on(dropPanel)
        assert dropPanel.title() == 'Dropped!'
    }

    @Test
    @DisplayName("Should use mouse with key modifiers")
    void keyModifiers() {
        Span span_Ctrl_mouseleft = $('#span_Ctrl_mouseleft') as Span
        Span span_Shift_mouseleft = $('#span_Shift_mouseleft') as Span

        assert !span_Ctrl_mouseleft.available()
        assert !span_Shift_mouseleft.available()

        CTRL.click $('#_Ctrl_mouseleft') as Div
        SHIFT.click $('#_Shift_mouseleft') as Div

        assert span_Ctrl_mouseleft.available()
        assert span_Shift_mouseleft.available()

        Span span_Alt_Shift_mouseleft = $('#span_Alt_Shift_mouseleft') as Span
        assert !span_Alt_Shift_mouseleft.available()
        (ALT + SHIFT).click $('#_Alt_Shift_mouseleft') as Div
        assert span_Alt_Shift_mouseleft.available()

        Span span_Crtl_Shift_mouseleft = $('#span_Crtl_Shift_mouseleft') as Span
        assert !span_Crtl_Shift_mouseleft.available()
        [CTRL, SHIFT].click $('#_Ctrl_Shift_mouseleft') as Div
        assert !span_Crtl_Shift_mouseleft.available()

        [SPACE].click $('#_Ctrl_Shift_mouseleft') as Div
        ['data'].click $('#_Ctrl_Shift_mouseleft') as Div
    }

    @CssIdentifier('div')
    class DropPanel extends Panel {
        String title() {
            provider.eval(id(), "it.find('h1').text()")
        }
    }
}
