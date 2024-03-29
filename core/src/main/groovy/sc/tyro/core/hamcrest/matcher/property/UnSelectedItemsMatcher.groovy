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
import sc.tyro.core.component.Item
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyItem
import sc.tyro.core.support.property.ItemSupport

import static java.lang.String.valueOf

/**
 * @author David Avenante
 * @since 1.0.0
 */
class UnSelectedItemsMatcher extends PropertyMatcher<ItemSupport> {
    private List<String> values = new ArrayList<>()
    private List<Item> items = new ArrayList<>()
    private List<Item> unSelectedItems

    UnSelectedItemsMatcher(String... values) {
        this.values = values
    }

    UnSelectedItemsMatcher(Item... items) {
        this.items = items
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        if (values) {
            items.clear()
            values.each { items.add(new DummyItem(it)) }
        }
        values.clear()
        items.each { values.add(valueOf(it.value())) }

        unSelectedItems = component.items().findAll{ item -> !item.selected() }
        unSelectedItems.size() == items.size() && unSelectedItems.containsAll(items)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedItems = new ArrayList<>()
        items.each { expectedItems.add(valueOf(it.value())) }

        description.appendText('unselected item(s) ')
        description.appendValueList('[', ', ', ']', expectedItems)
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        List<String> componentItems = new ArrayList<>()
        unSelectedItems.each { componentItems.add(valueOf(it.value())) }

        mismatchDescription.appendText('has unselected item(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentItems)
    }
}
