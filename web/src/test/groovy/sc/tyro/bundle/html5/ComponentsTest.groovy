package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.heading.*
import sc.tyro.bundle.html5.input.InputTypeCheckBox
import sc.tyro.bundle.html5.input.InputTypeEmail
import sc.tyro.bundle.html5.input.InputTypePassword
import sc.tyro.bundle.html5.input.InputTypeRadio
import sc.tyro.core.ComponentException
import sc.tyro.core.component.*
import sc.tyro.core.component.field.EmailField
import sc.tyro.core.support.property.TextSupport
import sc.tyro.web.CssIdentifier
import sc.tyro.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Html5 components")
class ComponentsTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    @DisplayName("Should have expected behaviours for Component")
    void component() {
        assert Button in sc.tyro.core.component.Button

        Button button = $('#button') as Button

        assert button.available()
        assert button.enabled()
        assert button.visible()

        button = $('#submit') as Button
        assert !button.enabled()

        Div panel = $('#hidden_panel') as Div
        assert !panel.visible()

        panel = $('#non_existing_id') as Div
        assert !panel.available()

        Form form = $('#form') as Form
        EmailField email = $('#email') as InputTypeEmail
        assert form.contains(email)
        assert !form.contains(button)
    }

    @Test
    @DisplayName("Should have expected behaviours for Article")
    void article() {
        assert Article in Component

        Article article = $('#article') as Article

        assert article.paragraphs.size() == 2
    }

    @Test
    @DisplayName("Should have expected behaviours for Aside")
    void aside() {
        assert Aside in Component

        Aside aside = $('#aside') as Aside

        assert aside.visible()
    }

    @Test
    @DisplayName("Should have expected behaviours for Button")
    void button() {
        assert Button in sc.tyro.core.component.Button

        // fields type=button
        Button button = $('#button') as Button

        assert Button in TextSupport
        // But override text
        assert button.text() == 'Button'

        // fields type=submit
        button = $('#submit') as Button
        assert button.text() == 'Submit'

        // fields type=reset
        button = $('#reset') as Button
        assert button.text() == 'Reset'

        // button element
        button = $('#btn') as Button
        assert button.text() == 'My Button Text'
    }

    @Test
    @DisplayName("Should have expected behaviours for Checkbox")
    void checkbox() {
        assert InputTypeCheckBox in CheckBox

        CheckBox checkBox = $('#checkbox') as InputTypeCheckBox
        assert checkBox.label() == 'Check me out'
        assert !checkBox.checked()
        checkBox.click()
        assert checkBox.checked()
        checkBox.click()
        assert !checkBox.checked()
        checkBox.click()
        assert checkBox.checked()

        Exception ex = assertThrows(ComponentException, { check checkBox })
        assert ex.message == 'InputTypeCheckBox InputTypeCheckBox:checkbox is already checked and cannot be checked'

        clickOn checkBox

        ex = assertThrows(ComponentException, { uncheck checkBox })
        assert ex.message == 'InputTypeCheckBox InputTypeCheckBox:checkbox is already unchecked and cannot be unchecked'

        checkBox = $('#disabled_checkbox') as InputTypeCheckBox
        ex = assertThrows(ComponentException, { check checkBox })
        assert ex.message == 'InputTypeCheckBox InputTypeCheckBox:disabled_checkbox is disabled and cannot be checked'

        ex = assertThrows(ComponentException, { uncheck checkBox })
        assert ex.message == 'InputTypeCheckBox InputTypeCheckBox:disabled_checkbox is disabled and cannot be unchecked'
    }

    @Test
    @DisplayName("Should have expected behaviours for Footer")
    void footer() {
        assert Footer in Component

        Footer footer = $('#footer') as Footer

        assert footer.visible()
    }

    @Test
    @DisplayName("Should have expected behaviours for Form")
    void form() {
        assert Form in sc.tyro.core.component.Form

        Form form = $('#form') as Form
        InputTypeEmail email = $('#form [type=email]') as InputTypeEmail
        InputTypePassword password = $('#form [type=password]') as InputTypePassword
        Message message = $('#form .alert') as Message

        assert form.visible()
        // Cause password mandatory
        assert !form.valid()

        email.value('joe.blow@email.org')
        password.value('password666')
        assert email.value() == 'joe.blow@email.org'
        assert password.value() == 'password666'

        form.reset()

        assert email.value() == ''
        assert password.value() == ''

        assert message.title() == 'The form was submitted 0 time(s)'
        // Set the required password field before submitting
        password.value('password666')
        form.submit()
        assert message.title() == 'The form was submitted 1 time(s)'
    }

    @Test
    @DisplayName("Should have expected behaviours for Header")
    void header() {
        assert Header in Component

        Header header = $('#header') as Header

        assert header.visible()
    }

    @Test
    @DisplayName("Should have expected behaviours for Heading")
    void heading() {
        assert H1 in Heading
        assert H2 in Heading
        assert H3 in Heading
        assert H4 in Heading
        assert H5 in Heading
        assert H6 in Heading

        H1 h1 = $('#h1') as H1
        assert h1.text() == 'heading 1'

        H2 h2 = $('#h2') as H2
        assert h2.text() == 'heading 2'

        H3 h3 = $('#h3') as H3
        assert h3.text() == 'heading 3'

        H4 h4 = $('#h4') as H4
        assert h4.text() == 'heading 4'

        H5 h5 = $('#h5') as H5
        assert h5.text() == 'heading 5'

        H6 h6 = $('#h6') as H6
        assert h6.text() == 'heading 6'
    }

    @Test
    @DisplayName("Should have expected behaviours for Image")
    void image() {
        assert Img in Image

        Img image = $('#image') as Img

        assert image.reference().endsWith('img/Montpellier.jpg')
    }

    @Test
    @DisplayName("Should have expected behaviours for Link")
    void link() {
        assert A in Link

        A link = $('#link') as A

        assert link.text() == 'Link external page'
        assert link.reference().contains('https://www.google.ca')
    }

    @Test
    @DisplayName("Should have expected behaviours for Panel")
    void panel() {
        assert Div in Panel

        Div panel = $('#panel') as Div

        assert panel.title() == ''
    }

    @Test
    @DisplayName("Should have expected behaviours for Paragraph")
    void paragraph() {
        assert P in Component
        assert P in TextSupport

        P paragraph = $('#p_1') as P

        assert paragraph.text() == 'Paragraph 1'
    }

    @Test
    @DisplayName("Should have expected behaviours for Radio")
    void radio() {
        assert InputTypeRadio in Radio

        Radio radio = $('#radio_1') as InputTypeRadio
        assert radio.label() == 'Radio checked'
        assert radio.checked()

        radio = $('#radio_2') as InputTypeRadio
        assert radio.label() == 'Radio unchecked'
        assert !radio.checked()
        radio.click()
        assert radio.checked()

        Exception ex = assertThrows(ComponentException, { check radio })
        assert ex.message == 'InputTypeRadio InputTypeRadio:radio_2 is already checked and cannot be checked'

        radio = $('#disabled_radio') as InputTypeRadio
        ex = assertThrows(ComponentException, { check radio })
        assert ex.message == 'InputTypeRadio InputTypeRadio:disabled_radio is disabled and cannot be checked'
    }

    @Test
    @DisplayName("Should have expected behaviours for Section")
    void section() {
        assert Section in Component

        Section section = $('#section') as Section

        assert section.paragraphs().size() == 1
        assert section.articles().size() == 1
    }

    @Test
    @DisplayName("Should have expected behaviours for Span")
    void span() {
        assert Span in Component

        Span span = $('#span') as Span

        assert span.text() == 'A span'
    }

    @Test
    @DisplayName("Should have expected behaviours for Label")
    void label() {
        assert Label in sc.tyro.core.component.Label

        Label label = $('[for=password_field]') as Label

        assert label.text() == 'Password'
    }

    @CssIdentifier('div')
    class Message extends Panel {
        @Override
        String title() {
            provider.eval(id(), "it.text()")
        }
    }
}
