package sc.tyro.web

import io.github.bonigarcia.wdm.WebDriverManager
import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import sc.tyro.core.Config

/**
 * @author David Avenante
 * @since 1.0.0
 */

class TyroTestExtension implements BeforeAllCallback, AfterAllCallback {
    private static Javalin app;
    private static WebDriver driver

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp")
        }).start(8080)

        WebDriverManager.chromedriver().setup()
        driver = new ChromeDriver();
        Config.provider = new SeleniumProvider(driver)
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        driver.close()
        app.stop()
    }
}
