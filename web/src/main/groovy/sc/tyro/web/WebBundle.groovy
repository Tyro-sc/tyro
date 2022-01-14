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

import org.openqa.selenium.WebDriver
import sc.tyro.core.Config
import sc.tyro.core.provider.NullScreenshotProvider
import sc.tyro.web.internal.WebIdentifiers

class WebBundle {
    static void init(WebDriver webDriver) {
        Config.provider = new SeleniumProvider(webDriver)
        Config.screenshotProvider = new NullScreenshotProvider()
        Config.identifiers = new WebIdentifiers()
        Config.scan("sc.tyro.bundle")
    }
}
