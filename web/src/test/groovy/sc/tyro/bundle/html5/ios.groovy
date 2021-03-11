package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.component.field.EmailField
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*

@ExtendWith(TyroWebTestExtension)
@DisplayName("Ios Tests")
class ios {

    @BeforeAll
    static void before() {
        visit 'https://tyro-sc.github.io/tyro-starters/'
    }

    @Test
    void clearOnIos() {
        EmailField email = field('Email')
        email.should {
            be visible
            be empty
        }

        fill email with 'my@email.org'

        email.should { have value('my@email.org') }

        clear email

        email.should {
            be visible
            be empty
        }
    }

}
