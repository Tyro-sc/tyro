package sc.tyro.bundle.html5.list

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.visit
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("DataList Tests")
class DataListTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'components.html'
    }

}
