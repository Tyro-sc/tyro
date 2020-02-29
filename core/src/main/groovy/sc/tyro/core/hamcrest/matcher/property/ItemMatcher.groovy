package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.Item
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyItem
import sc.tyro.core.support.property.ItemSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class ItemMatcher extends PropertyMatcher<ItemSupport> {
    private List<String> values = new ArrayList<>()
    private List<Item> items = new ArrayList<>()

    ItemMatcher(String... values) {
        this.values = values
    }

    ItemMatcher(Item... items) {
        this.items = items
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        if (values) {
            items.clear()
            values.each { items.add(new DummyItem(it)) }
        }
        values.clear()
        items.each { values.add(String.valueOf(it.value())) }
        component.items().size() == items.size() && component.items().containsAll(items)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedItems = new ArrayList<>()
        items.each { expectedItems.add(String.valueOf(it.value())) }

        description.appendText('item(s) ')
        description.appendValueList('[', ', ', ']', expectedItems)
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        List<String> componentItems = new ArrayList<>()
        component.items().each { componentItems.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has item(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentItems)
    }
}
