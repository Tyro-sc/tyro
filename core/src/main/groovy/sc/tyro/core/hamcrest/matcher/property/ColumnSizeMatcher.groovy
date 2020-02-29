package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.ColumnSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class ColumnSizeMatcher extends PropertyMatcher<ColumnSupport> {
    private Integer number

    ColumnSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(ColumnSupport component) {
        component.columns().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' column(s)')
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.columns().size()).appendText(' column(s)')
    }
}
