package sc.tyro.core.hamcrest.matcher.property.dummy

import sc.tyro.core.component.Item

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DummyItem extends Item {
    String value

    DummyItem(String value) {
        this.value = value
    }

    @Override
    boolean selected() { return false }

    @Override
    Object value() { return value }
}
