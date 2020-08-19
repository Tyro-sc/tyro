package sc.tyro.bundle.html5

import sc.tyro.bundle.html5.table.Tr
import sc.tyro.core.By
import sc.tyro.core.component.Component
import sc.tyro.core.support.property.TextSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('details')
class Details extends Component implements TextSupport {
    @Override
    String text() {
        provider.eval(id(), "it.contents().filter(function() { return this.nodeType == 3; }).text().trim()")
    }

    Summary summary() {
        provider.find(By.expression('#' + id() + ' summary'), Summary)
    }
}
