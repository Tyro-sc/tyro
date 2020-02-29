package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.component.Group
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.matcher.property.dummy.DummyGroup
import sc.tyro.core.support.property.GroupSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class GroupMatcher extends PropertyMatcher<GroupSupport> {
    private List<String> values = new ArrayList<>()
    private List<Group> groups = new ArrayList<>()

    GroupMatcher(String... values) {
        this.values = values
    }

    GroupMatcher(Group... groups) {
        this.groups = groups
    }

    @Override
    protected boolean matchesSafely(GroupSupport component) {
        if (values) {
            groups.clear()
            values.each { groups.add(new DummyGroup(it)) }
        }
        values.clear()
        groups.each { values.add(String.valueOf(it.value())) }
        component.groups().size() == groups.size() && component.groups().containsAll(groups)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedGroups = new ArrayList<>()
        groups.each { expectedGroups.add(String.valueOf(it.value())) }

        description.appendText('group(s) ')
        description.appendValueList('[', ', ', ']', expectedGroups)
    }

    @Override
    protected void describeMismatchSafely(GroupSupport component, Description mismatchDescription) {
        List<String> componentGroups = new ArrayList<>()
        component.groups().each { componentGroups.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has group(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentGroups)
    }
}
