package sc.tyro.doc

import org.junit.jupiter.api.Test
import sc.tyro.core.component.Button
import sc.tyro.core.component.Image
import sc.tyro.core.component.Panel
import sc.tyro.core.component.field.TextField

import java.awt.TextArea

class ComponentsTest {
    @Test
    void should_have_expected_properties_and_states_supported_by_button() {
        Button button = $('') as Button
        // tag::button[]
        button.should { have text('My Button Text') }
        // end::button[]
    }

    @Test
    void should_be_able_to_interact_with_mouse() {
        Button button = $('') as Button
        Image image = $('') as Image
        Panel panel = $('') as Panel

        // tag::mouse[]
        clickOn button
        doubleClickOn button
        rightClickOn button
        hoveringMouseOn button
        drag image on panel
        // end::mouse[]
    }

    @Test
    void should_be_able_to_interact_with_keyboard() {
        // tag::keyboard[]
        type('tyro') // tyro
        type(SHIFT + 'tyro') // => TYRO
        type(CTRL + ALT + SHIFT + 'x')
        // end::keyboard[]
    }

    @Test
    void test_text_area() {
        // tag::text_area[]

        TextField textarea = $('') as TextArea
        textarea.should {have length(350)}
        // end::text_area[]
    }
}
