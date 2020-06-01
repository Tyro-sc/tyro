package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.GroupSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class GroupSizeMatcher extends PropertyMatcher<GroupSupport> {
    private Integer number

    GroupSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(GroupSupport component) {
        component.groups().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' group(s)')
    }

    @Override
    protected void describeMismatchSafely(GroupSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.groups().size()).appendText(' group(s)')
    }
}

