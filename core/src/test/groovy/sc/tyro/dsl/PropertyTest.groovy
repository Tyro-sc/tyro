/*
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.dsl

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.*
import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.component.datagrid.DataGrid
import sc.tyro.core.component.datagrid.Row
import sc.tyro.core.component.field.RangeField
import sc.tyro.core.component.field.TextField

import static org.mockito.Mockito.*
import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Properties")
class PropertyTest {
    @Test
    @DisplayName("Should support text")
    void text() {
        Button button = spy(Button)

        doReturn('Text').when(button).text()

        button.should { have text('Text') }
    }

    @Test
    @DisplayName("Should support value")
    void value() {
        TextField field = spy(TextField)

        doReturn('Value').when(field).value()

        field.should { have value('Value') }
    }

    @Test
    @DisplayName("Should support error message")
    void errorMessage() {
        TextField field = spy(TextField)

        doReturn('Message').when(field).errorMessage()

        field.should { have errorMessage('Message') }
    }

    @Test
    @DisplayName("Should support length")
    void length() {
        TextField field = spy(TextField)

        doReturn(25).when(field).length()

        field.should { have length(25) }
    }

    @Test
    @DisplayName("Should support reference")
    void reference() {
        Link link = spy(Link)

        doReturn('https://reference').when(link).reference()

        link.should { have reference('https://reference') }
    }

    @Test
    @DisplayName("Should support label and placeholder")
    void labelAndPlaceholder() {
        TextField field = spy(TextField)

        doReturn('Label').when(field).label()

        field.should { have label('Label') }

        doReturn('placeholder').when(field).placeholder()

        field.should { have placeholder('placeholder') }
    }

    @Test
    @DisplayName("Should support maximum minimum and step")
    void maximumAndMinimumAndStep() {
        RangeField field = spy(RangeField)

        doReturn(5).when(field).minimum()
        doReturn(10).when(field).maximum()

        field.should {
            have minimum(5)
            have maximum(10)
        }

        doReturn(2).when(field).step()

        field.should { have step(2) }
    }

    @Test
    @DisplayName("Should support datagrid")
    void datagrid() {
        DataGrid datagrid = spy(DataGrid)

        Column column_1 = spy(Column)
        Column column_2 = spy(Column)

        doReturn([column_1, column_2]).when(datagrid).columns()

        datagrid.should { have columns(column_1, column_2) }

        doReturn('Column 1').when(column_1).title()
        doReturn('Column 2').when(column_2).title()

        datagrid.should { have columns('Column 1', 'Column 2') }

        Row row_1 = spy(Row)
        Row row_2 = spy(Row)

        doReturn([row_1, row_2]).when(datagrid).rows()

        datagrid.should { have rows(row_1, row_2) }

        doReturn('Row 1').when(row_1).title()
        doReturn('Row 2').when(row_2).title()

        datagrid.should { have rows('Row 1', 'Row 2') }
        row_1.should { have title('Row 1') }

        Cell cell_1 = spy(Cell)
        Cell cell_2 = spy(Cell)

        doReturn([cell_1, cell_2]).when(row_1).cells()

        row_1.should { have cells(cell_1, cell_2) }

        doReturn('Cell 1').when(cell_1).value()
        doReturn('Cell 2').when(cell_2).value()

        row_1.should { have cells('Cell 1', 'Cell 2') }
    }

    @Test
    @DisplayName("Should support dropdown")
    void dropdown() {
        Dropdown dropdown = spy(Dropdown)

        Group group_1 = spy(Group)
        Group group_2 = spy(Group)
        doReturn([group_1, group_2]).when(dropdown).groups()

        dropdown.should { have groups(group_1, group_2) }

        doReturn('Group 1').when(group_1).value()
        doReturn('Group 2').when(group_2).value()

        dropdown.should { have groups('Group 1', 'Group 2') }

        Item item_1 = spy(Item)
        Item item_2 = spy(Item)
        doReturn('Item 1').when(item_1).value()
        doReturn(true).when(item_1).selected()
        doReturn('Item 2').when(item_2).value()
        doReturn(true).when(item_2).selected()

        doReturn([item_1, item_2]).when(dropdown).items()

        dropdown.should { have items(item_1, item_2) }
        dropdown.should { have items('Item 1', 'Item 2') }

        doReturn([item_1]).when(dropdown).items()

        dropdown.should {
            have selectedItem(item_1)
            have selectedItem('Item 1')
        }
    }

    @Test
    @DisplayName("Should support listbox")
    void listbox() {
        ListBox listBox = spy(ListBox)
        Item item_1 = spy(Item)
        Item item_2 = spy(Item)
        Item item_3 = spy(Item)

        doReturn('Item 1').when(item_1).value()
        doReturn(true).when(item_1).selected()
        doReturn(true).when(item_1).enabled()
        doReturn(true).when(item_1).visible()

        doReturn('Item 2').when(item_2).value()
        doReturn(true).when(item_2).selected()
        doReturn(true).when(item_2).enabled()
        doReturn(false).when(item_2).visible()

        doReturn('Item 3').when(item_3).value()
        doReturn(false).when(item_3).selected()
        doReturn(false).when(item_3).enabled()
        doReturn(false).when(item_3).visible()

        doReturn([item_1, item_2, item_3]).when(listBox).items()

        listBox.should {
            have 2.selectedItems
            have selectedItems(item_1, item_2)
            have selectedItems('Item 1', 'Item 2')
            have 1.unSelectedItems
            have unSelectedItem(item_3)
            have unSelectedItem('Item 3')
            have unSelectedItems(item_3)
            have unSelectedItems('Item 3')
            have 1.disabledItems
            have disabledItem(item_3)
            have disabledItem('Item 3')
            have disabledItems(item_3)
            have disabledItems('Item 3')
            have 1.visibleItems
            have visibleItem(item_1)
            have visibleItem('Item 1')
            have visibleItems(item_1)
            have visibleItems('Item 1')
        }
    }
}
