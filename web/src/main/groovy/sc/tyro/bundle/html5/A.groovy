package sc.tyro.bundle.html5

import sc.tyro.core.component.Link
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('a')
class A extends Link {
    @Override
    String reference() {
        provider. eval(this.id(), "it.prop('href')")
    }

    @Override
    String text() {
        provider. eval(this.id(), "it.text()")
    }
}
