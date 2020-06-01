package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.StepSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class StepMatcher extends PropertyMatcher<StepSupport> {
    private Object step

    StepMatcher(Object step) {
        this.step = step
    }

    @Override
    protected boolean matchesSafely(StepSupport component) {
        component.step() == step
    }

    @Override
    void describeTo(Description description) {
        description.appendText('step ').appendText(step.toString())
    }

    @Override
    protected void describeMismatchSafely(StepSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has step ' + component.step().toString())
    }
}
