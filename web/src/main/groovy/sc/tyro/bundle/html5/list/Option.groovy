package sc.tyro.bundle.html5.list

import sc.tyro.core.component.Item
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('option')
class Option extends Item {
    @Override
    boolean selected() {
        provider.check(id(), "!!it.prop('selected')")
    }

    @Override
    Object value() {
        provider.eval(id(), "it.text().trim().length > 0 ? it.text().trim() : (it.attr('label') && it.attr('label').length > 0 ? it.attr('label') : '')")
    }

    @Override
    boolean enabled() {
        !provider.check(id(), "el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled')")
    }

    @Override
    boolean equals(Object o) {
        if (this.is(o)) return true
        value() == ((Option) o).value()
    }

    @Override
    int hashCode() {
        return value().hashCode()
    }

    @Override
    String toString() {
        return value()
    }
}
