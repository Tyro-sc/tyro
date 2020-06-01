package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.LabelSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class LabelMatcher extends PropertyMatcher<LabelSupport> {
    private String label

    LabelMatcher(String label) {
        this.label = label
    }

    @Override
    protected boolean matchesSafely(LabelSupport component) {
        component.label() == label
    }

    @Override
    void describeTo(Description description) {
        description.appendText('label ') appendValue(label)
    }

    @Override
    protected void describeMismatchSafely(LabelSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has label ').appendValue(component.label())
    }
}
