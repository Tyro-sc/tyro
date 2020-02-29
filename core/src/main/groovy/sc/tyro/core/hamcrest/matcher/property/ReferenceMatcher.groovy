package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.ReferenceSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ReferenceMatcher extends PropertyMatcher<ReferenceSupport> {
    String reference

    ReferenceMatcher(String reference) {
        this.reference = reference
    }

    @Override
    protected boolean matchesSafely(ReferenceSupport component) {
        component.reference() == reference
    }

    @Override
    void describeTo(Description description) {
        description.appendText('reference ').appendValue(reference)
    }

    @Override
    protected void describeMismatchSafely(ReferenceSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has reference ').appendValue(component.reference())
    }
}
