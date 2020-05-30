package sc.tyro.core.input

import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Mouse {
    static void clickOn(Component c) { c.click() }

    static void doubleClickOn(Component c) { c.doubleClick() }

    static void rightClickOn(Component c) { c.rightClick() }

    static void hoveringMouseOn(Component c) { c.mouseOver() }

    static DragBuilder drag(Component c) { return new DragBuilder(c) }
}
