package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.LengthSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class LengthMatcher extends PropertyMatcher<LengthSupport> {
    private Object length

    LengthMatcher(Object length) {
        this.length = length
    }

    @Override
    protected boolean matchesSafely(LengthSupport component) {
        component.length() == length
    }

    @Override
    void describeTo(Description description) {
        description.appendText('length ' + length.toString())
    }

    @Override
    protected void describeMismatchSafely(LengthSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has length ' + component.length().toString())
    }
}
