package sc.tyro.bundle.html5

import sc.tyro.core.By
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('article')
class Article extends Component {
    List<P> getParagraphs() {
        provider.findAll(By.expression('> p'), P)
    }
}
