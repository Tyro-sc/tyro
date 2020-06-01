package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.FocusSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class FocusedMatcher extends StateMatcher<FocusSupport> {
    @Override
    protected boolean matchesSafely(FocusSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is not focused')
        component.focused()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('focused')
    }
}