package sc.tyro.bundle.html5

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.TextSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('p')
class P extends Component implements TextSupport {
    @Override
    String text() {
        provider.eval(id(), "it.text()")
    }
}
