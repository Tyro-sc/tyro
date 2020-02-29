package sc.tyro.core.input

import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Mouse {
    public void clickOn(Component c) { c.click() }

    public void doubleClickOn(Component c) { c.doubleClick() }

    public void rightClickOn(Component c) { c.rightClick() }

    public void hoveringMouseOn(Component c) { c.mouseOver() }

    public DragBuilder drag(Component c) { return new DragBuilder(c) }
}
