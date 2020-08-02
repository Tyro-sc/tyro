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
package sc.tyro.web

import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.testcontainers.containers.BrowserWebDriverContainer

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL

/**
 * @author David Avenante
 * @since 1.0.0
 */
class TyroWebTestExtension implements BeforeAllCallback, AfterAllCallback {
    private static Javalin app
    private static WebDriver driver
    private static BrowserWebDriverContainer container
    public static String BASE_URL
    private boolean isLocal = Boolean.valueOf(System.getProperty("local"))

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp")
        }).start(8080)

        DatagramSocket socket = new DatagramSocket()
        socket.connect(InetAddress.getByName("8.8.8.8"), 10002)
        String host_ip = socket.getLocalAddress().getHostAddress()
        BASE_URL = "http://${host_ip}:8080/"

        if (isLocal) {
            if (System.getProperty("browser") == "firefox") {
                firefoxdriver().setup()
                driver = new FirefoxDriver()
                System.getProperties().setProperty("driver", "FirefoxDriver")
            } else {
                chromedriver().setup()
                driver = new ChromeDriver()
            }
        } else {
            Capabilities browser = new ChromeOptions()
            if (System.getProperty("browser") == "firefox") {
                browser = new FirefoxOptions()
                System.getProperties().setProperty("driver", "FirefoxDriver")
            }
            container = new BrowserWebDriverContainer()
                    .withCapabilities(browser)
                    .withRecordingMode(RECORD_ALL, new File("./target/"))
            container.start()

            driver = container.webDriver
        }

        WebBundle.init(driver)
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        driver.close()
        app.stop()
        if (!isLocal) {
            container.stop()
        }
    }
}
