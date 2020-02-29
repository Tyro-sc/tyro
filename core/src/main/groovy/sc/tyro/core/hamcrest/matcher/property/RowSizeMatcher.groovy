package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.RowSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class RowSizeMatcher extends PropertyMatcher<RowSupport> {
    private Integer number

    RowSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(RowSupport component) {
        component.rows().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' row(s)')
    }

    @Override
    protected void describeMismatchSafely(RowSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.rows().size()).appendText(' row(s)')
    }
}
