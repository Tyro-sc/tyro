package sc.tyro.bundle.html5.list

import sc.tyro.core.By
import sc.tyro.core.component.Group
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('optgroup')
class OptionGroup extends Group {
    List<Option> items() {
        provider.findAll(By.expression('#' + id() + ' option'), Option)
    }

    Option item(String value) {
        items().find { it.value() == value }
    }

    String value() {
        provider.eval(id(), "it.attr('label')")
    }
}
