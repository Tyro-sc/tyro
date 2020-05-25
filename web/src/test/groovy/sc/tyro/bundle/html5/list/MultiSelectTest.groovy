package sc.tyro.bundle.html5.list

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.ComponentException
import sc.tyro.core.component.Item
import sc.tyro.core.component.ListBox
import sc.tyro.bundle.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Multi Select Tests")
class MultiSelectTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    void should_have_expected_behaviours() {
        assert MultiSelect in ListBox

        ListBox empty_multi_select = $('#empty_multi_select') as MultiSelect
        empty_multi_select.should { be empty }

        ListBox cities = $('#cities') as MultiSelect

        assert cities.label() == 'Cities list'
        assert cities.items().size() == 6
        assert cities.visibleItems().size() == 3

        assert cities

        Item montreal = cities.item('Montreal')
        Item quebec = cities.item('Quebec')
        Item montpellier = cities.item('Montpellier')
        Item newYork = cities.item('New York')
        Item casablanca = cities.item('Casablanca')
        Item munich = cities.item('Munich')

        assert montreal.selected()
        assert montpellier.enabled()
        assert cities.item('Montreal').selected()

        assert !quebec.selected()
        assert !quebec.enabled()
        assert !cities.item('Quebec').selected()

        assert !montpellier.selected()
        assert !cities.item('Montpellier').selected()

        assert !newYork.selected()
        assert !cities.item('New York').selected()

        assert !casablanca.selected()
        assert !cities.item('Casablanca').selected()

        assert !munich.selected()
        assert !cities.item('Munich').selected()

        assert cities.selectedItems().containsAll(montreal)

        cities.unselect(montreal)
        cities.select(newYork, munich)

        assert cities.selectedItems().containsAll(newYork, munich)

        cities.select('Montpellier', 'Montreal')
        assert cities.item('Montpellier').selected()
        assert cities.item('Montreal').selected()
        assert cities.selectedItems().containsAll(newYork, munich, montpellier, montreal)

        cities.unselect(montreal)
        cities.unselect(montpellier)

        assert !cities.item('Montreal').selected()
        assert !cities.item('Montpellier').selected()
        assert cities.item('New York').selected()
        assert cities.item('Munich').selected()

        cities.select(montreal, montpellier)
        assert cities.item('Montreal').selected()
        assert cities.item('Montpellier').selected()
        assert cities.item('New York').selected()
        assert cities.item('Munich').selected()

        montpellier.click() // Now just Montpellier is selected
        assert montpellier.selected()
        assert !montreal.selected()
        assert !newYork.selected()
        assert !munich.selected()

        Exception ex = assertThrows(ComponentException, { cities.select(quebec) })
        assert ex.message == 'Option Quebec is disabled and cannot be selected'

        ex = assertThrows(ComponentException, { cities.unselect(quebec) })
        assert ex.message == 'Option Quebec is disabled and cannot be deselected'

        ex = assertThrows(ComponentException, { cities.unselect(newYork) })
        assert ex.message == 'Option New York is already unselected and cannot be deselected'

        ex = assertThrows(ComponentException, { cities.select(montpellier) })
        assert ex.message == 'Option Montpellier is already selected and cannot be selected'

        MultiSelect planets = $('#planets') as MultiSelect
        assert planets.visibleItems().size() == 5
        assert planets.groups().size() == 2
        assert planets.groups()[0].value() == 'Cat-1'
        assert planets.group('Cat-1').value() == 'Cat-1'

        Item venus = planets.item('Venus')
        Item saturn = planets.item('Saturn')

        assert planets.selectedItems().size() == 0
        planets.select('Venus', 'Saturn')

        assert planets.selectedItems().size() == 2
        assert planets.selectedItems().containsAll(venus, saturn)

        planets.unselect('Venus', 'Saturn')
        assert planets.selectedItems().size() == 0
    }
}