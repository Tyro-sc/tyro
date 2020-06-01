package sc.tyro.bundle.html5.heading

import sc.tyro.core.component.Heading
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('h2')
class H2 extends Heading {
    @Override
    String text() {
        provider.eval(id(), "it.text()")
    }
}
