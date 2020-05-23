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
import static sc.tyro.core.hamcrest.Matchers.cells
import static sc.tyro.core.hamcrest.Matchers.has

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Cell Property Matcher")
class CellMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        CellSupport cmp = mock(CellSupport)

        Cell cell_1 = mock(Cell)
        when(cell_1.value()).thenReturn('cell_1')
        Cell cell_2 = mock(Cell)
        when(cell_2.value()).thenReturn('cell_2')
        Cell cell_3 = mock(Cell)
        when(cell_3.value()).thenReturn('cell_3')

        when(cmp.cells()).thenReturn([cell_1, cell_2])

        assertThat(cmp, has(cells('cell_1', 'cell_2')))
        assertThat(cmp, has(cells(cell_1, cell_2)))

        Error error = assertThrows(AssertionError, { assertThat(cmp, has(cells('cell_1', 'cell_3'))) })
        assertThat(error.message, is('\nExpected: has cell(s) ["cell_1", "cell_3"]\n     but: has cell(s) ["cell_1", "cell_2"]'))

        error = assertThrows(AssertionError, { assertThat(cmp, has(cells(cell_1, cell_3))) })
        assertThat(error.message, is('\nExpected: has cell(s) ["cell_1", "cell_3"]\n     but: has cell(s) ["cell_1", "cell_2"]'))
    }
}
