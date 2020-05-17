package sc.tyro.core.input

import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Mouse {
    void clickOn(Component c) { c.click() }

    void doubleClickOn(Component c) { c.doubleClick() }

    void rightClickOn(Component c) { c.rightClick() }

    void hoveringMouseOn(Component c) { c.mouseOver() }

    DragBuilder drag(Component c) { return new DragBuilder(c) }
}
