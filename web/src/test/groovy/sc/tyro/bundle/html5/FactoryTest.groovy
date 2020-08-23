package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.By
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.visit
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Factories Tests")
class FactoryTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'factory.html'
    }

    @Test
    @DisplayName("Should find unique component by expression")
    void find() {
        Button button = provider.find(Button, By.expression('button'))

        assert button.visible()
    }

    @Test
    @DisplayName("Should find all components by expression")
    void findAllByExpression() {
        List<Button> buttons = provider.findAll(Button, By.expression('.btn-primary'))

        assert buttons.size() == 4
    }

    @Test
    @DisplayName("Should find all components by type")
    void findAll() {
        // Abstract class
        List<sc.tyro.core.component.Button> componentFromAbstract = provider.findAll(sc.tyro.core.component.Button)

        assert componentFromAbstract.size() == 5

        // Concrete class
        List<Button> componentFromConcrete = provider.findAll(Button)

        assert componentFromConcrete.size() == 5
    }
}
