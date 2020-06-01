package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.VisibleItemsSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class VisibleItemsSizeMatcher extends PropertyMatcher<VisibleItemsSupport> {
    private Integer number

    VisibleItemsSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(VisibleItemsSupport component) {
        component.visibleItems().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' visible item(s)')
    }

    @Override
    protected void describeMismatchSafely(VisibleItemsSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.visibleItems().size()).appendText(' visible item(s)')
    }
}
