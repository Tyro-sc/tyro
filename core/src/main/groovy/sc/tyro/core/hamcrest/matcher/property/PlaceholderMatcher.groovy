package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.InputSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class PlaceholderMatcher extends PropertyMatcher<InputSupport> {
    private String placeholder

    PlaceholderMatcher(String placeholder) {
        this.placeholder = placeholder
    }

    @Override
    protected boolean matchesSafely(InputSupport component) {
        component.placeholder() == placeholder
    }

    @Override
    void describeTo(Description description) {
        description.appendText('placeholder ').appendValue(placeholder)
    }

    @Override
    protected void describeMismatchSafely(InputSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has placeholder ').appendValue(component.placeholder())
    }
}
