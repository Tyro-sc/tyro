package sc.tyro.bundle.html5.table

import sc.tyro.core.component.datagrid.Cell
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('td')
class Td extends Cell {
    @Override
    Object value() {
        provider.eval(id(), "it.text().trim()")
    }
}
