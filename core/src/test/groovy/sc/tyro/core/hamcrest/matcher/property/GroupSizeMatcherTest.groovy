package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Group
import sc.tyro.core.support.property.GroupSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Group Size Property Matcher")
class GroupSizeMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        GroupSupport cmp = mock(GroupSupport)

        when(cmp.groups()).thenReturn([mock(Group), mock(Group)])

        MatcherAssert.assertThat(cmp, has(2.groups))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(3.groups))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has 3 group(s)\n     but: has 2 group(s)'))
    }
}
