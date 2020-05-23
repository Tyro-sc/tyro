package sc.tyro.bundle.html5

import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('label')
class Label extends sc.tyro.core.component.Label {
    @Override
    String text() {
        provider.eval(id(), "it.text()")
    }
}
