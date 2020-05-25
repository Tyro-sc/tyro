package sc.tyro.bundle.web

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.Div
import sc.tyro.bundle.html5.input.InputTypeText
import sc.tyro.core.Config

import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Selenium Provider Tests")
class SeleniumProviderTest {
    @Test
    void should_add_jquery_if_missing() {
        // Page with jquery missing
        visit 'http://localhost:8080/popup.html'

        assert 'Joe' == Config.provider.eval(null, "\$('#last_name').val('Joe').val()")
    }

    @Test
    void should_be_able_to_register_a_script() {
        // Page with jquery missing
        visit 'http://localhost:8080/popup.html'

        InputTypeText field = $('[id="first.name"]') as InputTypeText
        Div error = $('#firstname_blur') as Div

        assert field.empty()
        assert !error.visible()

        // Register scripts who
        // 1 - show the first name_blur message
        // 2 - set an email in email field
        Config.provider.registerScripts("function A_test() { \$('#firstname_blur').show()  }; A_test()")
        Config.provider.registerScripts("function B_test() { \$('[id=\"first.name\"]').val('Joe') }; B_test()")

        visit 'http://localhost:8080/popup.html'

        field = $('[id="first.name"]') as InputTypeText
        error = $('#firstname_blur') as Div

        assert !field.empty()
        assert error.visible()

        // Page with jquery already available
        visit 'http://localhost:8080/index.html'

        Div created = $('#created') as Div
        created.should { be missing }

        // Register scripts who
        // Create the missing TAG
        Config.provider.registerScripts("function create() { var element = document.createElement('div'); " +
                "element.id = 'created'; document.body.appendChild(element);}; create()")

        visit 'http://localhost:8080/index.html'

        created = $('#created') as Div
        created.should { be available }
    }
}
