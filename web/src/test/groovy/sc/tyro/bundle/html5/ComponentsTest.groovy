package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.web.TyroTestExtension

import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */

@ExtendWith(TyroTestExtension)
@DisplayName("Test core implementation with Html5 components ")
class ComponentsTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    void sample() {
        println "toto"
    }
}
