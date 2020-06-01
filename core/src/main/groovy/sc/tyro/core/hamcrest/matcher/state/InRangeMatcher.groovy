package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.RangeSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class InRangeMatcher extends StateMatcher<RangeSupport> {
    @Override
    protected boolean matchesSafely(RangeSupport item, Description mismatchDescription) {
        mismatchDescription.appendText('is out of range')
        item.inRange()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('in range')
    }
}