package sc.tyro.bundle.html5.table

import sc.tyro.core.By
import sc.tyro.core.component.datagrid.DataGrid
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('table')
class Table extends DataGrid {
    @Override
    List<Th> columns() {
        provider.findAll(By.expression('#' + id() + ' thead tr:last th'), Th)
    }

    @Override
    List<Tr> rows() {
        provider.findAll(By.expression('#' + id() + ' tbody tr'), Tr)
    }

    @Override
    Tr row(String title) {
        rows().find { it.title() == title }
    }

    @Override
    Th column(String title) {
        columns().find { it.title() == title }
    }

    @Override
    boolean empty() {
        rows().empty
    }
}
