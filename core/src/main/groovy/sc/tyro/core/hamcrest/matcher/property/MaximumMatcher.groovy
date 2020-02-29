package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.MaximumSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class MaximumMatcher extends PropertyMatcher<MaximumSupport> {
    private Object maximum

    MaximumMatcher(Object maximum) {
        this.maximum = maximum
    }

    @Override
    protected boolean matchesSafely(MaximumSupport component) {
        component.maximum() == maximum
    }

    @Override
    void describeTo(Description description) {
        description.appendText('maximum ' + maximum.toString())
    }

    @Override
    protected void describeMismatchSafely(MaximumSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has maximum ' + component.maximum().toString())
    }
}
