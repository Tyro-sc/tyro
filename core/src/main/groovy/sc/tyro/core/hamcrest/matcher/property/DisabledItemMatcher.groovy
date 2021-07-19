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
