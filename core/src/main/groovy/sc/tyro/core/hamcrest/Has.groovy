package sc.tyro.core.hamcrest

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Has<T> extends BaseMatcher<T> {
    private final Matcher<T> matcher

    Has(Matcher<T> matcher) {
        this.matcher = matcher
    }

    @Override
    boolean matches(Object item) {
        return matcher.matches(item)
    }

    @Override
    void describeTo(Description description) {
        description.appendText("has ").appendDescriptionOf(matcher)
    }

    @Override
    void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription)
    }
}