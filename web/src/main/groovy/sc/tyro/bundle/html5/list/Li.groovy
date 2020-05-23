package sc.tyro.bundle.html5.list

import sc.tyro.core.ComponentException
import sc.tyro.core.component.Item
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('li')
class Li extends Item {
    String value() {
        provider.eval(id(), 'it.text().trim()')
    }

    boolean selected() {
        throw new ComponentException('Unsupported Operation')
    }

    void select() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be selected (Unsupported Operation)")
    }

    void unselect() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be unselected (Unsupported Operation)")
    }

    boolean equals(Li o) {
        if (this.is(o)) return true
        return value() == o.value()
    }

    @Override
    String toString() {
        return value()
    }
}
