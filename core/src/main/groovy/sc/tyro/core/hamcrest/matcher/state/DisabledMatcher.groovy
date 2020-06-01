package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.component.Component
import sc.tyro.core.hamcrest.StateMatcher

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DisabledMatcher extends StateMatcher<Component> {
    @Override
    protected boolean matchesSafely(Component component, Description mismatchDescription) {
        mismatchDescription.appendText('is enabled')
        !component.enabled()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('disabled')
    }
}