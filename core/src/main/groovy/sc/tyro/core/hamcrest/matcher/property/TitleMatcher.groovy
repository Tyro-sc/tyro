package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.TitleSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class TitleMatcher extends PropertyMatcher<TitleSupport> {
    private String title

    TitleMatcher(String title) {
        this.title = title
    }

    @Override
    protected boolean matchesSafely(TitleSupport component) {
        component.title() == title
    }

    @Override
    void describeTo(Description description) {
        description.appendText('title ').appendValue(title)
    }

    @Override
    protected void describeMismatchSafely(TitleSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has title ').appendValue(component.title())
    }
}
