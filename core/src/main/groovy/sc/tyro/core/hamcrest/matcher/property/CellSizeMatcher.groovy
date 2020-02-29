package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.CellSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class CellSizeMatcher extends PropertyMatcher<CellSupport> {
    private Integer number

    CellSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(CellSupport component) {
        component.cells().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' cell(s)')
    }

    @Override
    protected void describeMismatchSafely(CellSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.cells().size()).appendText(' cell(s)')
    }
}
