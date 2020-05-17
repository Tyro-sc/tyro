package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class EmptyMatcher extends StateMatcher<EmptySupport> {
    @Override
    protected boolean matchesSafely(EmptySupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is filled')
        component.empty()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('empty')
    }
}