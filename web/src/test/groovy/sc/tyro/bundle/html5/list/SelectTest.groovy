package sc.tyro.bundle.html5.list

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.component.Dropdown
import sc.tyro.core.component.Group
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Select Tests")
class SelectTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'components.html'
    }

    // http://en.wikipedia.org/wiki/Drop-down_list
    @Test
    @DisplayName("Should have expected behaviours for Select")
    void select() {
        assert Select in Dropdown

        Select empty_select = $('#empty_select') as Select
        empty_select.should { be empty }

        Select elements = $('#elements') as Select

        assert elements.label() == 'Elements list'
        assert elements.items().size() == 5

        Dropdown os = $('#os') as Select
        assert os.items().size() == 8

        assert os.items()[1].value() == 'Ubuntu'
        assert os.item('Ubuntu').value() == 'Ubuntu'

        assert os.groups().size() == 3

        assert os.groups()[0].value() == 'linux'
        assert os.group('linux').value() == 'linux'
        assert os.group('win32').value() == 'win32'
        assert os.group('BSD').value() == 'BSD'

        assert os.selectedItem().value() == 'None'

        os.select('Fedora')
        assert os.selectedItem().value() == 'Fedora'
        os.select(os.items()[3])
        assert os.selectedItem().value() == 'Gentoo'

        Dropdown countries = $('#countries') as Select
        assert !countries.enabled()
        // Items are disabled too
        countries.items().forEach {
            assert !it.enabled()
        }
    }

    @Test
    void groupItem_should_have_expected_behaviours() {
        assert OptionGroup in Group

        Select os = $('#os') as Select
        Group linux = os.group('linux')

        assert linux.value() == 'linux'
        assert linux.items().size() == 3
        assert linux.item('Gentoo').value() == 'Gentoo'
    }

    @Test
    void implement_toString_and_equal() {
        Select os = $('#os') as Select

        assert os.items()[1].toString() == 'Ubuntu'
        assert os.items()[1] == os.items()[1]
    }
}
