package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.CheckSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class CheckedMatcher extends StateMatcher<CheckSupport> {
    @Override
    protected boolean matchesSafely(CheckSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is unchecked')
        component.checked()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('checked')
    }
}