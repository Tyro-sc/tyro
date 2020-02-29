package sc.tyro.core.hamcrest.matcher.property.dummy

import sc.tyro.core.component.Group
import sc.tyro.core.component.Item

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DummyGroup extends Group {
    String value

    DummyGroup(String value) {
        this.value = value
    }

    @Override
    List<Item> items() {
        return null
    }

    @Override
    Item item(String value) {
        return null
    }

    @Override
    String value() {
        return value
    }
}
