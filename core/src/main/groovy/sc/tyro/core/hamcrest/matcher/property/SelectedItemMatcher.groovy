package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.Item
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.SelectedItemSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class SelectedItemMatcher extends PropertyMatcher<SelectedItemSupport> {
    private String value
    private Item item

    SelectedItemMatcher(String value) {
        this.value = value
    }

    SelectedItemMatcher(Item item) {
        this.item = item
    }

    @Override
    protected boolean matchesSafely(SelectedItemSupport component) {
        if (value) {
            return component.selectedItem().value() == value
        }
        component.selectedItem() == item
    }

    @Override
    void describeTo(Description description) {
        description.appendText('selected item ').appendValue(value ? value : item.value())
    }

    @Override
    protected void describeMismatchSafely(SelectedItemSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has selected item ').appendValue(component.selectedItem().value())
    }
}
