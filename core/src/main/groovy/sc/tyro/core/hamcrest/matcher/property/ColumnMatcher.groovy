package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyColumn
import sc.tyro.core.support.property.ColumnSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class ColumnMatcher extends PropertyMatcher<ColumnSupport> {
    private List<String> values = new ArrayList<>()
    private List<Column> columns = new ArrayList<>()

    ColumnMatcher(String... values) {
        this.values = values
    }

    ColumnMatcher(Column... columns) {
        this.columns = columns
    }

    @Override
    protected boolean matchesSafely(ColumnSupport component) {
        if (values) {
            columns.clear()
            values.each { columns.add(new DummyColumn(it)) }
        }
        values.clear()
        columns.each { values.add(String.valueOf(it.title())) }
        component.columns().size() == columns.size() && component.columns().containsAll(columns)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedColumns = new ArrayList<>()
        columns.each { expectedColumns.add(String.valueOf(it.title())) }

        description.appendText('column(s) ')
        description.appendValueList('[', ', ', ']', expectedColumns)
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        List<String> componentColumns = new ArrayList<>()
        component.columns().each { componentColumns.add(String.valueOf(it.title())) }

        mismatchDescription.appendText('has column(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentColumns)
    }
}
