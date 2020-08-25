package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.SwitchSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class SwitchedOffMatcher extends StateMatcher<SwitchSupport> {
    @Override
    protected boolean matchesSafely(SwitchSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is on')
        !component.on()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('off')
    }
}