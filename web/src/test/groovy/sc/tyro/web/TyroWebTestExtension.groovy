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
import static java.lang.Boolean.valueOf
import static java.lang.System.getenv
import static java.net.InetAddress.getByName
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL

/**
 * @author David Avenante
 * @since 1.0.0
 */
class TyroWebTestExtension implements BeforeAllCallback, AfterAllCallback {
    private static Javalin app
    public static WebDriver driver
    private static BrowserWebDriverContainer container
    public static String BASE_URL
    private boolean isLocal = valueOf(getenv("local"))

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp")
        }).start(0)

        DatagramSocket socket = new DatagramSocket()
        socket.connect(getByName("8.8.8.8"), 10002)
        String host_ip = socket.localAddress.hostAddress
        BASE_URL = "http://${host_ip}:${app.port()}/"

        Capabilities options = capabilities()
        if (isLocal) {
            if (getenv("browser") == "firefox") {
                firefoxdriver().setup()
                driver = new FirefoxDriver(options)
            } else {
                chromedriver().setup()
                driver = new ChromeDriver(options)
            }
        } else {
            container = new BrowserWebDriverContainer()
                    .withCapabilities(options)
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

    private capabilities() {
        if (getenv("browser") == "firefox") {
            // For JUnit5 test usage
            System.setProperty("driver", "FirefoxDriver")
            Capabilities options = new FirefoxOptions()
            if (!isLocal) options.setHeadless(true)
            options.addArguments("--start-fullscreen")
            options.addArguments("--start-maximized")
            return options
        } else {
            Capabilities options = new ChromeOptions()
            options.addArguments("--start-fullscreen")
            if (!isLocal) options.setHeadless(true)
            return options
        }
    }
}
