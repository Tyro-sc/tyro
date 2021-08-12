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
package sc.tyro.web.screenshot

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper
import ru.yandex.qatools.ashot.shooting.ShootingStrategies
import sc.tyro.core.provider.ScreenshotProvider
import sc.tyro.core.component.Component

import javax.imageio.ImageIO
import java.nio.file.Files
import java.nio.file.Path

import static ru.yandex.qatools.ashot.cropper.indent.IndentFilerFactory.blur

class AShotProvider implements ScreenshotProvider {
    private final WebDriver webDriver

    AShotProvider(WebDriver webDriver) {
        this.webDriver = webDriver
    }

    @Override
    void takeScreenshot(String name, Component component = null) {
        Screenshot screenshot

        if(component) {
            screenshot = new AShot()
                    .imageCropper(new IndentCropper()
                            .addIndentFilter(blur()))
                    .coordsProvider(new WebDriverCoordsProvider())
                    .takeScreenshot(webDriver, webDriver.findElement(By.id(component.id())))
        } else {
            screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(webDriver)
        }

        Path target = Path.of(System.getProperty("user.dir"), 'target', 'screenshots', name + '.png')
        Files.createDirectories(target.getParent())

        ImageIO.write(screenshot.getImage(), "PNG", target.toFile())
    }
}
