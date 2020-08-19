package sc.tyro.bundle.html5

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.TextSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('legend')
class Legend extends Component implements TextSupport {
    @Override
    String text() {
        provider.eval(id(), "it.text()")
    }
}
