package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.input.InputTypeCheckBox
import sc.tyro.bundle.html5.input.InputTypeNumber
import sc.tyro.bundle.html5.input.InputTypePassword
import sc.tyro.core.ComponentException
import sc.tyro.core.Config
import sc.tyro.core.component.field.NumberField
import sc.tyro.core.component.field.PasswordField
import sc.tyro.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.ALT
import static sc.tyro.core.input.Key.CTRL
import static sc.tyro.core.input.MouseModifiers.DOUBLE
import static sc.tyro.core.input.MouseModifiers.RIGHT

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Errors Tests")
class ErrorsTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/errors.html'
    }

    @Test
    @DisplayName("Should not check already checked element")
    void alreadyChecked() {
        InputTypeCheckBox checkbox = $('#checkbox_1') as InputTypeCheckBox
        checkbox.should { be checked }

        Exception ex = assertThrows(ComponentException, { check checkbox })
        assert ex.message == 'InputTypeCheckBox InputTypeCheckBox:checkbox_1 is already checked and cannot be checked'
    }

    @Test
    @DisplayName("Should not be able to submit a form if no submit button available")
    void noSubmit() {
        Form form = $('#form') as Form

        Exception ex = assertThrows(ComponentException, { submit form })
        assert ex.message == 'Cannot submit form without submit button'
    }

    @Test
    @DisplayName("Should not be able to reset a form if no reset button available")
    void noReset() {
        Form form = $('#form') as Form

        Exception ex = assertThrows(ComponentException, { reset form })
        assert ex.message == 'Cannot reset form without reset button'
    }

    @Test
    @DisplayName("Should throw an error on invalid click sequence")
    void invalidClickSequence() {
        Form form = $('#form') as Form

        Exception ex = assertThrows(IllegalArgumentException, { [CTRL, 'test', ALT].click form })
        assert ex.message == 'Cannot type a modifier after some text'

        ex = assertThrows(IllegalArgumentException, { Config.provider.click (form, [RIGHT, DOUBLE], []) })
        assert ex.message == 'Invalid click sequence'
    }

    @Test
    @DisplayName("Should throw an error when asking length on input without length")
    void noLength() {
        PasswordField password = $('#password') as InputTypePassword

        Exception ex = assertThrows(ComponentException, { password.length() })
        assert ex.message == 'Not length defined for component InputTypePassword InputTypePassword:password'
    }

    @Test
    @DisplayName("Should throw an error when asking value on number field without value")
    void numberWithNoValue() {
        NumberField number = $('#number') as InputTypeNumber

        Exception ex = assertThrows(ComponentException, { number.value() })
        assert ex.message == 'InputTypeNumber InputTypeNumber:number is empty and has no value'
    }
}
