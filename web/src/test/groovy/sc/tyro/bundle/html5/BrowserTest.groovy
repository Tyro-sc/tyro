package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.Window
import sc.tyro.core.component.Component
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.$
import static sc.tyro.core.Tyro.browser
import static sc.tyro.core.Tyro.clickOn
import static sc.tyro.core.Tyro.visit
import static sc.tyro.core.Tyro.waitUntil
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Browser Tests")
class BrowserTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'index.html'
    }

    @Test
    @DisplayName("Should have expected properties")
    void properties() {
        browser().title == 'Tyro Rocks'
        browser().url == 'http://localhost:8080/index.html'
    }

    @Test
    @DisplayName("Should navigate")
    void navigate() {
        browser().url == 'http://localhost:8080/index.html'

        browser().navigateTo('http://localhost:8080/keyboard.html')
        browser().url == 'http://localhost:8080/index.html'

        browser().back()
        browser().url == 'http://localhost:8080/index.html'

        browser().forward()
        browser().url == 'http://localhost:8080/index.html'

        browser().refresh()
        browser().url == 'http://localhost:8080/index.html'
    }

    @Test
    @DisplayName("Should manage windows")
    void windows() {
        Component link = $('#link') as Component
        Component form = $('#dsl-form') as Component

        assert browser().windows.size() == 1
        assert link.available()
        assert !form.available()

        Window mainWindow = browser().windows[0]

        clickOn link

        waitUntil({ browser().windows.size() == 2 })
        browser().switchTo(browser().windows[1])
        assert form.available()

        browser().windows[1].close()
        waitUntil({ browser().windows.size() == 1 })
        assert browser().windows[0].id == mainWindow.id

        browser().switchTo(mainWindow)
    }
}
