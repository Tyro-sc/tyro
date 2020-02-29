package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.RangeSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class OutOfRangeMatcher extends StateMatcher<RangeSupport> {
    @Override
    protected boolean matchesSafely(RangeSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is in range')
        !component.inRange()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('out of range')
    }
}