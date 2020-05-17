package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.CollapseSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ExpandedMatcher extends StateMatcher<CollapseSupport> {
    @Override
    protected boolean matchesSafely(CollapseSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is collapsed')
        component.expanded()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('expanded')
    }
}