package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.ItemSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class ItemSizeMatcher extends PropertyMatcher<ItemSupport> {
    private Integer number

    ItemSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        component.items().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' item(s)')
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.items().size()).appendText(' item(s)')
    }
}
