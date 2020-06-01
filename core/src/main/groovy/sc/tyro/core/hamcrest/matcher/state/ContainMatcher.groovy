package sc.tyro.core.hamcrest.matcher.state

import org.hamcrest.Description
import sc.tyro.core.component.Component
import sc.tyro.core.hamcrest.StateMatcher

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ContainMatcher extends StateMatcher<Component> {
    private List<Component> components = new ArrayList<>()
    private Component container

    ContainMatcher(Component... components) {
        this.components.addAll(components)
    }

    @Override
    protected boolean matchesSafely(Component container, Description mismatchDescription) {
        this.container = container
        List<Component> ret = components.findAll({
            !container.contains(it)
        })

        mismatchDescription.appendText("does not contains expected component(s): ${ret.findAll { it }}")
        ret.empty
    }

    @Override
    void describeTo(Description description) {
        description.appendText("Component ${container} contains ${components}")
    }
}