package sc.tyro.core.input

import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class DragBuilder {
    private Component dragged

    public DragBuilder(Component dragged) {
        this.dragged = dragged
    }

    public void on(Component onto) {
        dragged.provider.dragAndDrop(dragged, onto)
    }
}