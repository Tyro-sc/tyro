package sc.tyro.bundle.html5

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.TextSupport
import sc.tyro.core.support.property.TitleSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('abbr')
class Abbr extends Component implements TextSupport, TitleSupport {
    @Override
    String text() {
        provider.eval(id(), "it.text()")
    }

    @Override
    String title() {
        provider.eval(id(), "it.prop('title')") as String
    }
}
