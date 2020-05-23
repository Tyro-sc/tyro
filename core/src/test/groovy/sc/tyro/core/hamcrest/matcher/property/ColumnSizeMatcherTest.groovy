package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.support.property.ColumnSupport

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
@DisplayName("Column Size Property Matcher")
class ColumnSizeMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ColumnSupport cmp = mock(ColumnSupport)

        when(cmp.columns()).thenReturn([mock(Column), mock(Column)])

        assertThat(cmp, has(2.columns))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(3.columns)) })
        assertThat(error.message, is('\nExpected: has 3 column(s)\n     but: has 2 column(s)'))
    }
}
