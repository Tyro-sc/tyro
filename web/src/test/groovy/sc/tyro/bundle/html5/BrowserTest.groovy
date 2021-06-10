/**
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.bundle.html5

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.Window
import sc.tyro.core.component.Component
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*
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
        browser().url == BASE_URL + ' index.html'
    }

    @Test
    @DisplayName("Should navigate")
    void navigate() {
        browser().url == BASE_URL + 'index.html'

        browser().navigateTo(BASE_URL + 'keyboard.html')
        browser().url == BASE_URL + 'index.html'

        browser().back()
        browser().url == BASE_URL + 'index.html'

        browser().forward()
        browser().url == BASE_URL + 'index.html'

        browser().refresh()
        browser().url == BASE_URL + 'index.html'
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
