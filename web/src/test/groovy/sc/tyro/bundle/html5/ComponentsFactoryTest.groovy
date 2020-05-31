package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.Config
import sc.tyro.core.component.Button
import sc.tyro.web.TyroWebTestExtension

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.hasSize
import static sc.tyro.core.Tyro.findBy
import static sc.tyro.core.Tyro.visit

@ExtendWith(TyroWebTestExtension)
@DisplayName("Components Factory Tests")
class ComponentsFactoryTest {
    @BeforeAll
    static void before() {
//        visit 'http://localhost:8080/factory.html'
        visit 'http://localhost:8080/components.html'
    }

    @Disabled
    @Test
    @DisplayName("Should find components by type")
    void findByType() {
        Config.provider.eval(null, "\$('#range_field').val(20)")

//        "it.val(" + value + ")")

//        List<Button> buttons = findBy(Button)
//
//        assertThat(buttons, hasSize(5))

    }

}
