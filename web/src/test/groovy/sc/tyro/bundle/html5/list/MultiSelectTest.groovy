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
import org.junit.jupiter.api.condition.DisabledIfSystemProperty
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.ComponentException
import sc.tyro.core.component.Item
import sc.tyro.core.component.ListBox
import sc.tyro.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Multi Select Tests")
class MultiSelectTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'components.html'
    }

    @Test
    @DisplayName("Should have expected behaviours for MultiSelect")
    @DisabledIfSystemProperty(named = "driver", matches = "FirefoxDriver") // Open a new issue see README.md
    void multiSelect() {
        assert MultiSelect in ListBox

        ListBox empty_multi_select = $('#empty_multi_select') as MultiSelect
        empty_multi_select.should { be empty }

        ListBox cities = $('#cities') as MultiSelect

        cities.should {
            have label('Cities list')
            have 6.items
            have 3.visibleItems
        }

        Item montreal = cities.item('Montreal')
        Item quebec = cities.item('Quebec')
        Item montpellier = cities.item('Montpellier')
        Item newYork = cities.item('New York')
        Item casablanca = cities.item('Casablanca')
        Item munich = cities.item('Munich')

        cities.visibleItems().containsAll([montreal, quebec, montpellier])

        montreal.should { be selected }
        montpellier.should {
            be enabled
            be unselected
        }
        quebec.should {
            be disabled
            be unselected
        }
        newYork.should { be unselected }
        casablanca.should { be unselected }
        munich.should { be unselected }

        // TODO: cities.should { have selectedItem(montreal) }
        cities.should { have selectedItems(montreal) }

        cities.unselect(montreal)
        cities.select(newYork, munich)

        cities.should { have selectedItems(newYork, munich) }

        cities.select('Montpellier', 'Montreal')
        cities.should { have selectedItems('Montreal', 'Montpellier', 'New York', 'Munich') }
        cities.should { have selectedItems(montreal, montpellier, newYork, munich) }

        cities.unselect(montreal)
        cities.unselect(montpellier)

        montpellier.should { be unselected }
        montpellier.should { be unselected }
        newYork.should { be selected }
        munich.should { be selected }

        cities.select(montreal, montpellier)
        montreal.should { be selected }
        montpellier.should { be selected }
        newYork.should { be selected }
        munich.should { be selected }

        montpellier.click() // Now just Montpellier is selected
        montpellier.should { be selected }
        montreal.should { be unselected }
        newYork.should { be unselected }
        munich.should { be unselected }

        Exception ex = assertThrows(ComponentException, { cities.select(quebec) })
        assert ex.message == 'Option Quebec is disabled and cannot be selected'

        ex = assertThrows(ComponentException, { cities.unselect(quebec) })
        assert ex.message == 'Option Quebec is disabled and cannot be deselected'

        ex = assertThrows(ComponentException, { cities.unselect(newYork) })
        assert ex.message == 'Option New York is already unselected and cannot be deselected'

        ex = assertThrows(ComponentException, { cities.select(montpellier) })
        assert ex.message == 'Option Montpellier is already selected and cannot be selected'

        MultiSelect planets = $('#planets') as MultiSelect
        planets.should {
            have 8.items
            have 5.visibleItems
            have 2.groups
        }

        planets.groups()[0].should { have value('Cat-1') }
        planets.groups()[1].should { have value('Cat-2') }

        Item venus = planets.item('Venus')
        Item saturn = planets.item('Saturn')

        planets.should { have 0.selectedItems }
        planets.select('Venus', 'Saturn')
        planets.should {
            have 2.selectedItems
            have 6.unSelectedItems
            selectedItems(venus, saturn)
        }

        planets.unselect('Venus', 'Saturn')
        planets.should { have 0.selectedItems }
    }
}