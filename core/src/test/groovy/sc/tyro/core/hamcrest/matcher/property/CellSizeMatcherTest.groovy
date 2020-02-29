package sc.tyro.core.hamcrest.matcher.property

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.support.property.CellSupport

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
@DisplayName("Cell Size Property Matcher")
class CellSizeMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        CellSupport cmp = mock(CellSupport)

        when(cmp.cells()).thenReturn([mock(Cell), mock(Cell)])

        assertThat(cmp, has(2.cells))

        AssertionError error = assertThrows(AssertionError, {
            assertThat(cmp, has(3.cells))
        }) as AssertionError

        assertThat(error.message, is('\nExpected: has 3 cell(s)\n     but: has 2 cell(s)'))
    }
}
