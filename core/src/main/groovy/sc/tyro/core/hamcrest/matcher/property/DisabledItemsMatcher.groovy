package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.Item
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.ItemSupport

import static java.lang.String.valueOf

class DisabledItemsMatcher extends PropertyMatcher<ItemSupport> {
    private List<String> values = new ArrayList<>()
    private List<Item> items = new ArrayList<>()

    DisabledItemsMatcher(String... values) {
        this.values = values
    }

    DisabledItemsMatcher(Item... items) {
        this.items = items
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        if (values) {
            items.clear()
            values.each { items.add(component.item(it)) }
        }
        values.clear()
        items.each { values.add(valueOf(it.value())) }
        component.items().findAll { item -> !item.enabled() }.containsAll(items)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedItems = new ArrayList<>()
        items.each { item -> expectedItems.add(valueOf(item.value())) }

        description.appendText('disabled item(s) ')
        description.appendValueList('[', ', ', ']', expectedItems)
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        List<String> componentItems = new ArrayList<>()
        component.items().findAll { item -> !item.enabled() }.each { componentItems.add(valueOf(it.value())) }

        mismatchDescription.appendText('has disabled item(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentItems)
    }
}