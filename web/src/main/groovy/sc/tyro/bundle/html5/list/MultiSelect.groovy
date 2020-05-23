package sc.tyro.bundle.html5.list

import sc.tyro.bundle.html5.helper.LabelHelper
import sc.tyro.core.By
import sc.tyro.core.component.ListBox
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier("select[multiple]")
class MultiSelect extends ListBox {
    @Override
    List<Option> items() {
        provider.findAll(By.expression('#' + id() + ' option'), Option)
    }

    @Override
    Option item(String value) {
        items().find { it.value() == value }
    }

    @Override
    List<Option> visibleItems() {
        int size = provider.eval(id(), "it.prop('size')") as Integer
        items()[0..size - 1]
    }

    @Override
    List<OptionGroup> groups() {
        provider.findAll(By.expression('#' + id() + ' optgroup'), OptionGroup)
    }

    @Override
    OptionGroup group(String value) {
        groups().find { it.value() == value }
    }

    List<Option> selectedItems() {
        items().findAll { it.selected() }
    }

    @Override
    String label() {
        LabelHelper.label(this)
    }

    @Override
    boolean empty() {
        items().empty
    }
}
