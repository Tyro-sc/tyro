package sc.tyro.bundle.html5

import sc.tyro.core.By
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('fieldset')
class FieldSet extends Component {
    Legend legend() {
        provider.find(By.expression('#' + id() + ' legend'), Legend)
    }
}
