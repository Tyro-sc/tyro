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

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.io.FileHandler
import sc.tyro.core.component.Component
import sc.tyro.core.provider.ScreenshotProvider
import java.nio.file.Paths

import static java.nio.file.Files.createDirectories
import static org.openqa.selenium.OutputType.FILE

class SeleniumScreenshotProvider implements ScreenshotProvider {
    private final WebDriver webDriver
    private final String directory

    SeleniumScreenshotProvider(WebDriver webDriver, String directory = System.getProperty("user.dir") + "/target/screenshots") {
        this.webDriver = webDriver
        this.directory = directory
    }

    @Override
    void takeScreenshot(String name, Component component) {
        createDirectories(Paths.get(this.directory))
        File source
        if (component) {
            WebElement element = webDriver.findElement(By.id(component.id()))
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({behavior:'auto',block:'center',inline:'center'});", element)
            source = element.getScreenshotAs(FILE)
        } else {
            source = ((TakesScreenshot) webDriver).getScreenshotAs(FILE)
        }
        File dest = new File(directory + '/' + name + '.png')
        FileHandler.copy(source, dest)
    }
}
