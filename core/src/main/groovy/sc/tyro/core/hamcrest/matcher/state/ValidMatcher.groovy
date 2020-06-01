package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.ValiditySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ValidMatcher extends StateMatcher<ValiditySupport> {
    @Override
    protected boolean matchesSafely(ValiditySupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is invalid')
        component.valid()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('valid')
    }
}