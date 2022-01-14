/*
 * Copyright © 2021 Ovea (d.avenante@gmail.com)
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
import sc.tyro.core.component.datagrid.Row
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyRow
import sc.tyro.core.support.property.RowSupport

import static java.lang.String.valueOf

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
        rows.each { values.add(valueOf(it.title())) }
        component.rows().size() == rows.size() && component.rows().containsAll(rows)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedRows = new ArrayList<>()
        rows.each { expectedRows.add(valueOf(it.title())) }

        description.appendText('row(s) ')
        description.appendValueList('[', ', ', ']', expectedRows)
    }

    @Override
    protected void describeMismatchSafely(RowSupport component, Description mismatchDescription) {
        List<String> componentRows = new ArrayList<>()
        component.rows().each { componentRows.add(valueOf(it.title())) }

        mismatchDescription.appendText('has row(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentRows)
    }
}
