package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.web.TyroWebTestExtension

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.not
import static org.hamcrest.io.FileMatchers.anExistingFile
import static sc.tyro.core.Tyro.takeScreenshot
import static sc.tyro.core.Tyro.visit
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Screenshot Tests")
class ScreenshotTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'components.html'
    }

    @Test
    @DisplayName("Should take window screenshot")
    void windowScreenshot() {
        File screenshot = new File("target/screenshots/window.png")

        assertThat(screenshot, not(anExistingFile()))

        takeScreenshot('window')

        assertThat(screenshot, anExistingFile())
    }

    @Test
    @DisplayName("Should take component screenshot")
    void componentScreenshot() {

    }
}
