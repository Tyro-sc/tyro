package sc.tyro.bundle.html5.table

import sc.tyro.core.By
import sc.tyro.core.component.datagrid.Row
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('tr')
class Tr extends Row {
    @Override
    List<Td> cells() {
        provider.findAll(By.expression("\$('[id=\"${id()}\"]').find('td')"), Td)
    }

    @Override
    Td cell(Object value) {
        cells().find { it.value() == value }
    }

    @Override
    String title() {
        provider.eval(id(), "it.find('th:first').text().trim()")
    }
}
