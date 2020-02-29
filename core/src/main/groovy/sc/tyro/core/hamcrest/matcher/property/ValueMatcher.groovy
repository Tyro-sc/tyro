package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.ValueSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ValueMatcher extends PropertyMatcher<ValueSupport> {
    private Object value

    ValueMatcher(Object value) {
        this.value = value
    }

    @Override
    protected boolean matchesSafely(ValueSupport component) {
        component.value() == value
    }

    @Override
    void describeTo(Description description) {
        description.appendText('value ') appendValue(value)
    }

    @Override
    protected void describeMismatchSafely(ValueSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has value ').appendValue(component.value())
    }
}
