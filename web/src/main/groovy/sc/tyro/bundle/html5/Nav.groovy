package sc.tyro.bundle.html5

import sc.tyro.core.By
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('nav')
class Nav extends Component {
    List<A> links() {
        provider.findAll(By.expression('#' + id() + ' > a'), A)
    }
}
