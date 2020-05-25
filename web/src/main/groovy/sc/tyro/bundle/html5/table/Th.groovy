package sc.tyro.bundle.html5.table

import sc.tyro.core.By
import sc.tyro.core.component.datagrid.Column
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('th')
class Th extends Column {
    @Override
    List<Td> cells() {
        int index = provider.eval(id(), "it.index() + 1") as int
        provider.findAll(By.expression("\$('[id=\"${id()}\"]').closest('table').find('tbody tr').find('td:nth-child(${index})')"), Td)
    }

    @Override
    Td cell(Object value) {
        cells().find { it.value() == value }
    }

    @Override
    String title() {
        provider.eval(id(), "it.text().trim()")
    }
}
