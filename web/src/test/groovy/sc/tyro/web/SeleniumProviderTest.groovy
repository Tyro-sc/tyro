/*
 * Copyright © 2021 Ovea (d.avenante@gmail.com)
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
package sc.tyro.web


import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.Div
import sc.tyro.bundle.html5.Img
import sc.tyro.bundle.html5.input.InputTypeText
import sc.tyro.core.Config
import sc.tyro.core.component.Image

import java.nio.file.Path

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.not
import static org.hamcrest.io.FileMatchers.anExistingFile
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Selenium Provider Tests")
class SeleniumProviderTest {

    @Test
    @DisplayName("Should add jquery if missing")
    void jquery() {
        // Page with jquery missing
        visit BASE_URL + 'popup.html'

        assert 'Joe' == Config.provider.eval(null, "\$('#last_name').val('Joe').val()")
    }

    @Test
    @DisplayName("Should be able to register scripts")
    void registerScripts() {
        // Page with jquery missing
        visit BASE_URL + 'popup.html'

        InputTypeText field = $('[id="first.name"]') as InputTypeText
        Div error = $('#firstname_blur') as Div

        assert field.empty()
        assert !error.visible()

        // Register scripts who:
        // 1 - show the first name_blur message
        // 2 - set an email in email field
        Config.provider.registerScripts("function A_test() { \$('#firstname_blur').show()  }; A_test()")
        Config.provider.registerScripts("function B_test() { \$('[id=\"first.name\"]').val('Joe') }; B_test()")

        visit BASE_URL + 'popup.html'

        field = $('[id="first.name"]') as InputTypeText
        error = $('#firstname_blur') as Div

        assert !field.empty()
        assert error.visible()

        // Page with jquery already available
        visit BASE_URL + 'index.html'

        Div created = $('#created') as Div
        created.should { be missing }

        // Register scripts who
        // Create the missing TAG
        Config.provider.registerScripts("function create() { var element = document.createElement('div'); " +
                "element.id = 'created'; document.body.appendChild(element);}; create()")

        visit BASE_URL + 'index.html'

        created = $('#created') as Div
        created.should { be available }
    }

    @Test
    @DisplayName("Should be able to take screenshots")
    void takeScreenshot() {
        visit BASE_URL + 'components.html'

        File screenshotDir = Path.of(System.getProperty("user.dir"), 'target', 'screenshots').toFile()
        screenshotDir.deleteDir()

        File componentScreenshot = new File("target/screenshots/montpellier.png")
        File pageScreenshot = new File("target/screenshots/page.png")

        assertThat(componentScreenshot, not(anExistingFile()))
        assertThat(pageScreenshot, not(anExistingFile()))

        Image image = $('#image') as Img
        takeScreenshot("montpellier", image)

        takeScreenshot("page")

        assertThat(componentScreenshot, anExistingFile())
        assertThat(pageScreenshot, anExistingFile())
    }
}
