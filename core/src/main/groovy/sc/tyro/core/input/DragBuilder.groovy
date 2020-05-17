package sc.tyro.core.input

import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DragBuilder {
    private Component dragged

    DragBuilder(Component dragged) {
        this.dragged = dragged
    }

    void on(Component onto) {
        dragged.provider.dragAndDrop(dragged, onto)
    }
}