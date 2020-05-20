package sc.tyro.web

import org.openqa.selenium.WebDriver
import sc.tyro.core.Config
import sc.tyro.web.internal.WebIdentifiers

class WebBundle {
    static void init(WebDriver webDriver) {
        Config.provider = new SeleniumProvider(webDriver)
        Config.identifiers = new WebIdentifiers()
        Config.scan("sc.tyro.bundle")
    }
}
