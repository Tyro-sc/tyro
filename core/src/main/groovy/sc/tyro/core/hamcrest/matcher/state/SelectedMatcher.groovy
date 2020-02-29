package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.SelectSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class SelectedMatcher extends StateMatcher<SelectSupport> {
    @Override
    protected boolean matchesSafely(SelectSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is unselected')
        component.selected()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('selected')
    }
}