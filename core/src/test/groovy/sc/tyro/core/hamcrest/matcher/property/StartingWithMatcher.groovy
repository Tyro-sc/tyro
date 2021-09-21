package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class StartingWithMatcher extends TypeSafeMatcher<String> {
    @Override
    protected boolean matchesSafely(String string) {
        return false
    }

    @Override
    void describeTo(Description description) {

    }
}
