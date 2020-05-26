package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.input.InputTypeText
import sc.tyro.web.TyroWebTestExtension
import sc.tyro.core.Config
import sc.tyro.core.component.Component

import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Keyboard Tests")
class KeyboardTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/keyboard.html'
    }

    @Test
    @DisplayName("Should type letters")
    void letters() {
        browser().refresh()

        (0..25).each {
            char letter = (char) (('a' as char) + it)
            Component current_span = $("#span_$letter")
            current_span.should { be missing }
            type "$letter"
            current_span.should { be available }
        }
    }

    @Test
    @DisplayName("Should type numbers")
    void numbers() {
        browser().refresh()

        (0..9).each {
            Component current_span = $("#span_$it")
            current_span.should { be missing }
            type "$it"
            current_span.should { be available }
        }
    }

    /**
     * Most of the browsers of the market don't ever propagate all keyboard events for some keys.
     * So in this tests we just test keys that can be really handle by a web page
     * So we excluded :
     *  F1 to F12
     *  ESCAPE, INSERT, DELETE, PAGE_UP, PAGE_DOWN, HOME, END, BACK_SPACE, TAB, LEFT, UP, RIGHT, DOWN
     */
    @Test
    @DisplayName("Should type special keys")
    void specialKeys() {
        browser().refresh()

        Config.provider

        [
//                '#span_divide'   : DIVIDE,
//                '#span_multiply' : MULTIPLY,
                '#span_substract': SUBTRACT,
                '#span_add'      : ADD,
                '#span_equals'   : EQUALS,
                '#span_return'   : RETURN,
                '#span_space'    : SPACE
        ].each { k, v ->
            Component current_span = $(k)
            current_span.should { be missing }
            type v
            println "====> " + v
            current_span.should { be available }
        }

        Component span = $('#span_Ctrl_Alt_Shift_x')
        assert !span.available()
        type(CTRL + ALT + SHIFT + 'x')
        assert span.available()
    }

    @Test
    @DisplayName("Should use Key Modifiers")
    void keyModifiers() {
        browser().refresh()

        InputTypeText textField = $('#textfield') as InputTypeText

        assert textField.value() == ''
        clickOn textField
        type(SHIFT + 'tyro')
        textField.should { have value('TYRO') }

        textField.clear()
        textField.should { have value('') }
        type('~!@#$%^&*()_+')
        textField.should { have value('~!@#$%^&*()_+') }

        textField.clear()
        textField.should { have value('') }
        type(SHIFT + '`1234567890-=')
        textField.should { have value('~!@#$%^&*()_+') }
    }
}
