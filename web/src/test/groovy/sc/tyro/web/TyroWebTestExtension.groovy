package sc.tyro.web

import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver

/**
 * @author David Avenante
 * @since 1.0.0
 */
class TyroWebTestExtension implements BeforeAllCallback, AfterAllCallback {
    private static Javalin app
    private static WebDriver driver

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp")
        }).start(8080)

//        firefoxdriver().setup()
//        driver = new FirefoxDriver()

        chromedriver().setup()
        driver = new ChromeDriver()

        System.getProperties().setProperty("driver", driver.class.simpleName)
        WebBundle.init(driver)
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        driver.close()
        app.stop()
    }
}
