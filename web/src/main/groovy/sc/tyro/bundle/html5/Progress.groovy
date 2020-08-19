package sc.tyro.bundle.html5

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.MaximumSupport
import sc.tyro.core.support.property.ValueSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('progress')
class Progress extends Component implements MaximumSupport, ValueSupport {
    @Override
    Object maximum() {
        provider.eval(id(), "it.prop('max')") as BigDecimal
    }

    @Override
    Object value() {
        provider.eval(id(), "it.prop('value')") as BigDecimal
    }
}
