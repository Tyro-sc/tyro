package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.support.state.ReadOnlySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class ReadOnlyMatcher extends StateMatcher<ReadOnlySupport> {
    @Override
    protected boolean matchesSafely(ReadOnlySupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is not read only')
        component.readOnly()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('read only')
    }
}