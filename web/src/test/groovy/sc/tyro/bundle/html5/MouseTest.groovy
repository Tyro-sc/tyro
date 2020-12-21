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
package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.web.CssIdentifier
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Mouse Tests")
class MouseTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'mouse.html'
    }

    @Test
    @DisplayName("Should click")
    void click() {
        Button button = $('#button_1') as Button

        button.should { have text('Button') }
        clickOn button
        button.should { have text('Button Clicked!') }

        browser().refresh()

        button = $('#button_1') as Button
        button.should { have text('Button') }
        button.click()
        button.should { have text('Button Clicked!') }
    }

    @Test
    @DisplayName("Should double click")
    void doubleClick() {
        Button button = $('#button_2') as Button

        button.should { have text('Button') }
        doubleClickOn button
        button.should { have text('Button Double Clicked!') }

        browser().refresh()
        sleep(1000L)

        button = $('#button_2') as Button

        button.should { have text('Button') }
        button.doubleClick()
        button.should { have text('Button Double Clicked!') }
    }

    @Test
    @DisplayName("Should right click")
    void rightClick() {
        Button button = $('#button_5') as Button

        button.should { have text('Button') }
        rightClickOn button
        button.should { have text('Button Right Clicked!') }

        browser().refresh()

        button = $('#button_5') as Button

        button.should { have text('Button') }
        button.rightClick()
        button.should { have text('Button Right Clicked!') }
    }

    @Test
    @DisplayName("Should mouse over")
    void mouseOver() {
        Button button = $('#button_3') as Button
        button.should { have text('Button') }

        hoveringMouseOn button

        button.should { have text('Button Mouse Over!') }
    }

    @Test
    @DisplayName("Should mouse out")
    void mouseOut() {
        Button button = $('#button_4') as Button
        button.should { have text('Button') }

        // To simulate mouse out
        // 1 - mouse over the component
        hoveringMouseOn button
        // 2 - mouse over an another component
        hoveringMouseOn $('#button_5') as Button
        // The mouse out is triggered
        button.should { have text('Button Mouse Out!') }
    }

    @Test
    @DisplayName("Should drag and drop")
    void dragAndDrop() {
        DropPanel dropPanel = $('#drop-zone') as DropPanel
        dropPanel.should { have title('Drop here') }

        Div dragPanel = $('#drag') as Div
        drag dragPanel on dropPanel
        dropPanel.should { have title('Dropped!') }

        browser().refresh()

        dropPanel = $('#drop-zone') as DropPanel
        dropPanel.should { have title('Drop here') }

        dragPanel = $('#drag') as Div
        dragPanel.drag().on(dropPanel)
        dropPanel.should { have title('Dropped!') }
    }

    @Test
    @DisplayName("Should use mouse with key modifiers")
    void keyModifiers() {
        Span span_Ctrl_mouseleft = $('#span_Ctrl_mouseleft') as Span
        Span span_Shift_mouseleft = $('#span_Shift_mouseleft') as Span

        span_Ctrl_mouseleft.should { be missing }
        span_Shift_mouseleft.should { be missing }

        CTRL.click $('#_Ctrl_mouseleft') as Div
        SHIFT.click $('#_Shift_mouseleft') as Div

        span_Ctrl_mouseleft.should { be available }
        span_Shift_mouseleft.should { be available }

        Span span_Alt_Shift_mouseleft = $('#span_Alt_Shift_mouseleft') as Span
        span_Alt_Shift_mouseleft.should { be missing }

        (ALT + SHIFT).click $('#_Alt_Shift_mouseleft') as Div
        span_Alt_Shift_mouseleft.should { be available }

        Span span_Crtl_Shift_mouseleft = $('#span_Crtl_Shift_mouseleft') as Span
        span_Crtl_Shift_mouseleft.should { be missing }

        [CTRL, SHIFT].click $('#_Ctrl_Shift_mouseleft') as Div
        span_Crtl_Shift_mouseleft.should { be missing }
    }

    @CssIdentifier('div')
    class DropPanel extends Div {
        String title() {
            provider.eval(id(), "it.find('h1').text()")
        }
    }
}
