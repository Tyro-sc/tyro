package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Item
import sc.tyro.core.support.property.VisibleItemsSupport

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
@DisplayName("Visible Items Size Property Matcher")
class VisibleItemsSizeMatcherTest {
    @Test
    @DisplayName("Should support matcher Visible")
    void matcher() {
        VisibleItemsSupport cmp = mock(VisibleItemsSupport)

        when(cmp.visibleItems()).thenReturn([mock(Item), mock(Item)])

        assertThat(cmp, has(2.visibleItems))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(3.visibleItems)) })
        assertThat(error.message, is('\nExpected: has 3 visible item(s)\n     but: has 2 visible item(s)'))
    }
}
