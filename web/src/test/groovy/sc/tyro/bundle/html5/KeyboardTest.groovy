package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.input.InputTypeText
import sc.tyro.core.component.Component
import sc.tyro.web.TyroWebTestExtension

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
            Component span = $("#span_$letter")
            span.should { be missing }
            type "$letter"
            span.should { be available }
        }
    }

    @Test
    @DisplayName("Should type numbers")
    void numbers() {
        browser().refresh()

        (0..9).each {
            Component span = $("#span_$it")
            span.should { be missing }
            type "$it"
            span.should { be available }
        }
    }

    /**
     * Most of the browsers of the market don't ever propagate all keyboard events for some keys.
     * So in this tests we just test keys that can be really handle by a web page
     * So we excluded :
     *  F10 to F12
     */
    @Test
    @DisplayName("Should type F1..F12")
    void FKeys() {
        browser().refresh()

        (1..12).each {
            Component span = $("#span_F$it")
            span.should { be missing }
        }

        type F1
        type F2
        type F3
        type F4
        type F5
        type F6
        type F7
        type F8
        type F9
//        type F10
//        type F11
//        type F12

        (1..9).each {
            Component span = $("#span_F$it")
            span.should { be available }
        }
    }

    /**
     * Most of the browsers of the market don't ever propagate all keyboard events for some keys.
     * So in this tests we just test keys that can be really handle by a web page
     * So we excluded :
     *  ESCAPE, INSERT, DELETE, PAGE_UP, PAGE_DOWN, HOME, END, BACK_SPACE, TAB, LEFT, UP, RIGHT, DOWN
     */
    @Test
    @DisplayName("Should type special keys")
    void specialKeys() {
        browser().refresh()
        [
                '#span_backspace': BACK_SPACE,
                '#span_tab'      : TAB,
                '#span_clear'    : CLEAR,
                '#span_enter'    : ENTER,
//                '#span_return'    : RETURN,
                '#span_escape'   : ESCAPE,
                '#span_space'    : SPACE,
                '#span_left'     : LEFT,
                '#span_up'       : UP,
                '#span_right'    : RIGHT,
                '#span_down'     : DOWN,
                '#span_delete'   : DELETE,
                '#span_insert'   : INSERT,
                '#span_home'     : HOME,
                '#span_end'      : END,
                '#span_pageup'   : PAGE_UP,
                '#span_pagedown' : PAGE_DOWN,


//                shift: 16,
//                 altKey
//                '⌥': 18,
//                alt: 18,
//                option: 18,
//                 ctrlKey
//                '⌃': 17,
//                ctrl: 17,
//                control: 17,
//                 metaKey
//                '⌘': 91,
//                cmd: 91,
//                command: 91,


//                NULL,
//                CANCEL,
//                HELP,
//                META,
//                COMMAND,
//                PAUSE,
//
//                SEPARATOR,
//                DECIMAL,
//                SEMICOLON,
//
//                INSERT,
//                DELETE,
//
//                MULTIPLY,
//                DIVIDE,
//                SUBTRACT,
//                ADD,
//                EQUALS,
//
//                ARROW_LEFT,
//                ARROW_UP,
//                ARROW_RIGHT,
//                ARROW_DOWN,
//
//                SHIFT,
//                LEFT_SHIFT,
//                CTRL,
//                LEFT_CTRL,
//                ALT,
//                LEFT_ALT
//                NUMPAD0,
//                NUMPAD1,
//                NUMPAD2,
//                NUMPAD3,
//                NUMPAD4,
//                NUMPAD5,
//                NUMPAD6,
//                NUMPAD7,
//                NUMPAD8,
//                NUMPAD9,

//                '#span_substract': SUBTRACT,
//                '#span_add'      : ADD,
//                '#span_equals'   : EQUALS,
//                '#span_return'   : RETURN,
//                '#span_space'    : SPACE
        ].each { k, v ->
            Component span = $(k)
            span.should { be missing }
            type v
            println "====> " + v
            span.should { be available }
        }

        Component span = $('#span_Ctrl_r')
        assert !span.available()
        type(CTRL + 'r')
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
