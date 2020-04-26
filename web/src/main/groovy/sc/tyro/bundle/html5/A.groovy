package sc.tyro.bundle.html5

import sc.tyro.core.component.Link
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante (d.avenante@gmail.com)
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
