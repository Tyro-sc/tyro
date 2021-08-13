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

import com.applitools.eyes.selenium.Eyes
import org.openqa.selenium.By
import sc.tyro.core.component.Component
import sc.tyro.core.provider.ScreenshotProvider

class EyesProvider implements ScreenshotProvider {
    private final Eyes eyes

    EyesProvider(Eyes eyes) {
        this.eyes = eyes
    }

    @Override
    void takeScreenshot(String name, Component component) {
        if (component) {
            eyes.checkElement(By.id(component.id()))
            return
        }
        eyes.checkWindow(name)
    }
}
