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

import com.applitools.eyes.selenium.Eyes
import io.percy.selenium.Percy
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.bundle.html5.table.Table
import sc.tyro.core.Config
import sc.tyro.core.component.datagrid.DataGrid
import sc.tyro.web.TyroWebTestExtension
import sc.tyro.web.screenshot.AShotProvider
import sc.tyro.web.screenshot.EyesProvider
import sc.tyro.web.screenshot.PercyProvider

import java.nio.file.Path

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.not
import static org.hamcrest.io.FileMatchers.anExistingFile
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@TestMethodOrder(OrderAnnotation)
@DisplayName("Screenshot Tests")
class ScreenshotTest {
    @BeforeAll
    static void before() {
        File screenshotDir = Path.of(System.getProperty("user.dir"), 'target', 'screenshots').toFile()
        screenshotDir.deleteDir()

        visit BASE_URL + 'components.html'
    }

    @Test
    @DisplayName("Should take window screenshot")
    @Order(1)
    void windowScreenshot() {
        File screenshot = new File("target/screenshots/window.png")

        assertThat(screenshot, not(anExistingFile()))

        takeScreenshot('window')

        assertThat(screenshot, anExistingFile())
    }

    @Test
    @DisplayName("Should take component screenshot")
    @Order(2)
    void componentScreenshot() {
        DataGrid data_grid = $('#data_grid') as Table
        File screenshot = new File("target/screenshots/component.png")

        assertThat(screenshot, not(anExistingFile()))

        takeScreenshot('component', data_grid)

        assertThat(screenshot, anExistingFile())
    }

    @Test
    @Order(3)
    @DisplayName("Should use AShot Screenshot provider by default")
    void defaultScreenshotProvider() {
        assert Config.screenshotProvider instanceof AShotProvider
    }

    @Test
    @Order(3)
    @DisplayName("Should setup Percy Screenshot provider")
    void percyScreenshotProvider() {
        Percy percy = new Percy(TyroWebTestExtension.driver)
        Config.screenshotProvider = new PercyProvider(percy)

        assert Config.screenshotProvider instanceof PercyProvider

        takeScreenshot('percy')
    }

    @Test
    @Order(4)
    @DisplayName("Should setup Applitools Eyes Screenshot provider")
    void eyesScreenshotProvider() {
        EyesProvider eyesProvider = mock(EyesProvider)
        Config.screenshotProvider = eyesProvider

        assert Config.screenshotProvider instanceof EyesProvider

        takeScreenshot('eyes')

        verify(eyesProvider, times(1)).takeScreenshot('eyes', null)
    }
}