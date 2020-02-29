package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.RequiredSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class OptionalMatcher extends StateMatcher<RequiredSupport> {
    @Override
    protected boolean matchesSafely(RequiredSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is required')
        !component.required()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('optional')
    }
}