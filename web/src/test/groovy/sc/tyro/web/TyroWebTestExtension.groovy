package sc.tyro.web

import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import sc.tyro.core.Config

import static io.github.bonigarcia.wdm.WebDriverManager.*

/**
 * @author David Avenante
 * @since 1.0.0
 */

class TyroWebTestExtension implements BeforeAllCallback, AfterAllCallback {
    private static Javalin app;
    private static WebDriver driver

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp")
        }).start(8080)

        chromedriver().setup()
        driver = new ChromeDriver()
        WebBundle.init(driver)
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        driver.close()
        app.stop()
    }
}
