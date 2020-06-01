package sc.tyro.bundle.html5

import sc.tyro.core.By
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('section')
class Section extends Component {
    List<P> paragraphs() {
        provider.findAll(By.expression('#' + id() + ' > p'), P)
    }

    List<Article> articles() {
        provider.findAll(By.expression('#' + id() + ' > article'), Article)
    }
}
