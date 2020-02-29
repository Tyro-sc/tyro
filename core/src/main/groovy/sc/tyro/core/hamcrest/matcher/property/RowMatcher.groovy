package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.datagrid.Row
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyRow
import sc.tyro.core.support.property.RowSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class RowMatcher extends PropertyMatcher<RowSupport> {
    private List<String> values = new ArrayList<>()
    private List<Row> rows = new ArrayList<>()

    RowMatcher(String... values) {
        this.values = values
    }

    RowMatcher(Row... rows) {
        this.rows = rows
    }

    @Override
    protected boolean matchesSafely(RowSupport component) {
        if (values) {
            rows.clear()
            values.each { rows.add(new DummyRow(it)) }
        }
        values.clear()
        rows.each { values.add(String.valueOf(it.title())) }
        component.rows().size() == rows.size() && component.rows().containsAll(rows)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedRows = new ArrayList<>()
        rows.each { expectedRows.add(String.valueOf(it.title())) }

        description.appendText('row(s) ')
        description.appendValueList('[', ', ', ']', expectedRows)
    }

    @Override
    protected void describeMismatchSafely(RowSupport component, Description mismatchDescription) {
        List<String> componentRows = new ArrayList<>()
        component.rows().each { componentRows.add(String.valueOf(it.title())) }

        mismatchDescription.appendText('has row(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentRows)
    }
}
