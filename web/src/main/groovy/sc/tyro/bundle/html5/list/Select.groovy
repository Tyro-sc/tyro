package sc.tyro.bundle.html5.list

import sc.tyro.core.By
import sc.tyro.core.component.Dropdown
import sc.tyro.web.CssIdentifier

import static sc.tyro.bundle.html5.input.Label.findFor

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier("select:not([multiple])")
class Select extends Dropdown {
    @Override
    List<Option> items() {
        provider.findAll(By.expression('#' + id() + ' option'), Option)
    }

    @Override
    List<OptionGroup> groups() {
        provider.findAll(By.expression('#' + id() + ' optgroup'), OptionGroup)
    }

    @Override
    OptionGroup group(String value) {
        groups().find { it.value() == value }
    }

    @Override
    Option item(String value) {
        items().find { it.value() == value }
    }

    @Override
    Option selectedItem() {
        items().find { it.selected() }
    }

    @Override
    String label() {
        findFor(this)
    }

    @Override
    boolean empty() {
        items().empty
    }
}
