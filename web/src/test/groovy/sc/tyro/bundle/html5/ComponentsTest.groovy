package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.$
import static sc.tyro.core.Tyro.visit

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Test core implementation with Html5 components ")
class ComponentsTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    void component_should_have_expected_common_behaviours() {
        assert Button in sc.tyro.core.component.Button

        Button button = $('#button') as Button

        assert button.enabled()
        assert button.available()
        assert button.visible()

        button = $('#submit') as Button
        assert !button.enabled()

        Div panel = $('#hidden_panel') as Div
        assert !panel.visible()

        panel = $('#non_existing_id') as Div
        assert !panel.available()
        By
    }
}
