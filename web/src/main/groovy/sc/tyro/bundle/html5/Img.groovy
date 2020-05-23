package sc.tyro.bundle.html5

import sc.tyro.core.component.Image
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('img')
class Img extends Image {
    @Override
    String reference() {
        provider.eval(id(), "it.prop('src')")
    }
}
