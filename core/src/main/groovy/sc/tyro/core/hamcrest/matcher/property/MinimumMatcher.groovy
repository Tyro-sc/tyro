package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.MinimumSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class MinimumMatcher extends PropertyMatcher<MinimumSupport> {
    private Object minimum

    MinimumMatcher(Object minimum) {
        this.minimum = minimum
    }

    @Override
    protected boolean matchesSafely(MinimumSupport component) {
        component.minimum() == minimum
    }

    @Override
    void describeTo(Description description) {
        description.appendText('minimum ' + minimum.toString())
    }

    @Override
    protected void describeMismatchSafely(MinimumSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has minimum ' + component.minimum())
    }
}
