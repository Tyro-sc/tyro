package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.TextSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class TextMatcher extends PropertyMatcher<TextSupport> {
    private String text

    TextMatcher(String text) {
        this.text = text
    }

    @Override
    protected boolean matchesSafely(TextSupport component) {
        component.text() == text
    }

    @Override
    void describeTo(Description description) {
        description.appendText('text ').appendValue(text)
    }

    @Override
    protected void describeMismatchSafely(TextSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has text ').appendValue(component.text())
    }
}
