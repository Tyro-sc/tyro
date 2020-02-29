package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.ValiditySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class InvalidMatcher extends StateMatcher<ValiditySupport> {
    @Override
    protected boolean matchesSafely(ValiditySupport item, Description mismatchDescription) {
        mismatchDescription.appendText('is valid')
        !item.valid()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('invalid')
    }
}