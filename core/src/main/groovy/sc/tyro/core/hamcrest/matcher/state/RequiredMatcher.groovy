package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.RequiredSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class RequiredMatcher extends StateMatcher<RequiredSupport> {
    @Override
    protected boolean matchesSafely(RequiredSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is optional')
        component.required()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('required')
    }
}