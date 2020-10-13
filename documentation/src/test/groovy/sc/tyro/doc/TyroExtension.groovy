package sc.tyro.doc

import io.github.bonigarcia.wdm.WebDriverManager
import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import sc.tyro.web.WebBundle

import static org.openqa.selenium.remote.BrowserType.CHROME
import static org.openqa.selenium.remote.BrowserType.FIREFOX

class TyroExtension implements BeforeAllCallback, AfterAllCallback {
    private static Javalin app
    private static WebDriver webDriver

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp")
        }).start(8080)

        // Add -DbrowserType=firefox/chrome/... to you VM Option to select the browser
        String browser = System.getProperty("browserType")
        if (!browser) {
            println "No browser selected. Use Firefox"
            browser = FIREFOX
        }

        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup()
                webDriver = new FirefoxDriver()
                break
            case CHROME:
                WebDriverManager.chromedriver().setup()
                webDriver = new ChromeDriver()
                break
        }
        WebBundle.init(webDriver)
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        webDriver.quit()
        app.stop()
    }
}
