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
import sc.tyro.core.support.property.ItemSupport

class DisabledItemMatcher extends PropertyMatcher<ItemSupport> {
    private String value
    private Item item

    DisabledItemMatcher(String value) {
        this.value = value
    }

    DisabledItemMatcher(Item item) {
        this.item = item
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        if (value) {
            return component.items().find { item -> !item.enabled() }.value() == value
        }
        component.items().find { item -> !item.enabled() } == item
    }

    @Override
    void describeTo(Description description) {
        description.appendText('disabled item ').appendValue(value ? value : item.value())
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has disabled item ').appendValue(component.items().find { item -> !item.enabled() }.value())
    }
}
