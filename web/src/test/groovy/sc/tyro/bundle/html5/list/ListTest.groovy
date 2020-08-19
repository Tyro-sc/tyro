/**
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
package sc.tyro.bundle.html5.list

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.ComponentException
import sc.tyro.core.component.ListView
import sc.tyro.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.$
import static sc.tyro.core.Tyro.visit
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("List Tests")
class ListTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'components.html'
    }

    @Test
    @DisplayName("Should have expected behaviours for List")
    void list() {
        assert Ul in ListView

        Ul ul = $('#empty_unordered_list') as Ul
        assert ul.empty()

        ul = $('#unordered_list') as Ul

        assert ul.items().size() == 5
        assert ul.items()[0].value() == 'Item 1'
        assert ul.item('Item 4').value() == 'Item 4'
        assert ul.items()[3].equals(ul.items()[4])
        assert ul.items()[3].toString() == 'Item 4'

        assert Ol in ListView

        Ol ol = $('#empty_ordered_list') as Ol
        assert ol.empty()

        ol = $('#ordered_list') as Ol

        assert ol.items().size() == 5
        assert ol.items()[0].value() == 'Item 11'
        assert ol.item('Item 44').value() == 'Item 44'

        // Html5 list items can't be selected
        Exception ex = assertThrows(ComponentException, { ol.items()[0].selected() })
        assert ex.message == 'Unsupported Operation'

        ex = assertThrows(ComponentException, { !ol.items()[0].selected() })
        assert ex.message == 'Unsupported Operation'

        ex = assertThrows(ComponentException, { ol.items()[0].select() })
        assert ex.message == 'Li Item 11 cannot be selected (Unsupported Operation)'

        ex = assertThrows(ComponentException, { ol.items()[0].unselect() })
        assert ex.message == 'Li Item 11 cannot be unselected (Unsupported Operation)'
    }
}
