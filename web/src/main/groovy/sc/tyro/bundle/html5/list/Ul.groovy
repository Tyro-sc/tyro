package sc.tyro.bundle.html5.list

import sc.tyro.core.By
import sc.tyro.core.component.ListView
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('ul')
class Ul extends ListView {
    @Override
    List<Li> items() {
        provider.findAll(By.expression('#' + id() + ' li'), Li)
    }

    @Override
    Li item(String value) {
        items().find { it.value() == value }
    }

    @Override
    boolean empty() {
        items().empty
    }
}