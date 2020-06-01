package sc.tyro.web

import org.openqa.selenium.Keys
import sc.tyro.core.input.Key

import static sc.tyro.core.input.Key.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
class KeyConverter {
    static Keys convert(Key key) {
        switch (key) {
            case ESCAPE:
                return Keys.ESCAPE
            case NULL:
                return Keys.NULL
            case CANCEL:
                return Keys.CANCEL
            case HELP:
                return Keys.HELP
            case CLEAR:
                return Keys.CLEAR
            case META:
                return Keys.META
            case COMMAND:
                return Keys.COMMAND
            case PAUSE:
                return Keys.PAUSE
            case SEPARATOR:
                return Keys.SEPARATOR
            case DECIMAL:
                return Keys.DECIMAL
            case SEMICOLON:
                return Keys.SEMICOLON

            case F1:
                return Keys.F1
            case F2:
                return Keys.F2
            case F3:
                return Keys.F3
            case F4:
                return Keys.F4
            case F5:
                return Keys.F5
            case F6:
                return Keys.F6
            case F7:
                return Keys.F7
            case F8:
                return Keys.F8
            case F9:
                return Keys.F9
            case F10:
                return Keys.F10
            case F11:
                return Keys.F11
            case F12:
                return Keys.F12

            case NUMPAD0:
                return Keys.NUMPAD0
            case NUMPAD1:
                return Keys.NUMPAD1
            case NUMPAD2:
                return Keys.NUMPAD2
            case NUMPAD3:
                return Keys.NUMPAD3
            case NUMPAD4:
                return Keys.NUMPAD4
            case NUMPAD5:
                return Keys.NUMPAD5
            case NUMPAD6:
                return Keys.NUMPAD6
            case NUMPAD7:
                return Keys.NUMPAD7
            case NUMPAD8:
                return Keys.NUMPAD8
            case NUMPAD9:
                return Keys.NUMPAD9

            case INSERT:
                return Keys.INSERT
            case DELETE:
                return Keys.DELETE
            case PAGE_UP:
                return Keys.PAGE_UP
            case PAGE_DOWN:
                return Keys.PAGE_DOWN
            case HOME:
                return Keys.HOME
            case END:
                return Keys.END
            case BACK_SPACE:
                return Keys.BACK_SPACE

            case MULTIPLY:
                return Keys.MULTIPLY
            case DIVIDE:
                return Keys.DIVIDE
            case SUBTRACT:
                return Keys.SUBTRACT
            case ADD:
                return Keys.ADD
            case EQUALS:
                return Keys.EQUALS

            case TAB:
                return Keys.TAB
            case RETURN:
                return Keys.RETURN
            case ENTER:
                return Keys.ENTER
            case SPACE:
                return Keys.SPACE

            case LEFT:
                return Keys.LEFT
            case UP:
                return Keys.UP
            case RIGHT:
                return Keys.RIGHT
            case DOWN:
                return Keys.DOWN

            case ARROW_LEFT:
                return Keys.ARROW_LEFT
            case ARROW_UP:
                return Keys.ARROW_UP
            case ARROW_RIGHT:
                return Keys.ARROW_RIGHT
            case ARROW_DOWN:
                return Keys.ARROW_DOWN

            case SHIFT:
                return Keys.SHIFT
            case LEFT_SHIFT:
                return Keys.LEFT_SHIFT
            case CTRL:
                return Keys.CONTROL
            case LEFT_CTRL:
                return Keys.LEFT_CONTROL
            case ALT:
                return Keys.ALT
            case LEFT_ALT:
                return Keys.LEFT_ALT
        }
    }
}