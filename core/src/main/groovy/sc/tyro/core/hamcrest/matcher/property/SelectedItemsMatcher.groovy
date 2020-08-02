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
import sc.tyro.core.component.Item
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyItem
import sc.tyro.core.support.property.SelectedItemsSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class SelectedItemsMatcher extends PropertyMatcher<SelectedItemsSupport> {
    private List<String> values = new ArrayList<>()
    private List<Item> items = new ArrayList<>()

    SelectedItemsMatcher(String... values) {
        this.values = values
    }

    SelectedItemsMatcher(Item... items) {
        this.items = items
    }

    @Override
    protected boolean matchesSafely(SelectedItemsSupport component) {
        if (values) {
            items.clear()
            values.each { items.add(new DummyItem(it)) }
        }
        values.clear()
        items.each { values.add(String.valueOf(it.value())) }
        component.selectedItems().size() == items.size() && component.selectedItems().containsAll(items)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedItems = new ArrayList<>()
        items.each { expectedItems.add(String.valueOf(it.value())) }

        description.appendText('selected item(s) ')
        description.appendValueList('[', ', ', ']', expectedItems)
    }

    @Override
    protected void describeMismatchSafely(SelectedItemsSupport component, Description mismatchDescription) {
        List<String> componentItems = new ArrayList<>()
        component.selectedItems().each { componentItems.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has selected item(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentItems)
    }
}
