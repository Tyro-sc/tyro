/**
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyColumn
import sc.tyro.core.support.property.ColumnSupport

import static java.lang.String.valueOf

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ColumnMatcher extends PropertyMatcher<ColumnSupport> {
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
        columns.each { values.add(valueOf(it.title())) }
        component.columns().size() == columns.size() && component.columns().containsAll(columns)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedColumns = new ArrayList<>()
        columns.each { expectedColumns.add(valueOf(it.title())) }

        description.appendText('column(s) ')
        description.appendValueList('[', ', ', ']', expectedColumns)
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        List<String> componentColumns = new ArrayList<>()
        component.columns().each { componentColumns.add(valueOf(it.title())) }

        mismatchDescription.appendText('has column(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentColumns)
    }
}
