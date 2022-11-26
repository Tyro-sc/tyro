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

import io.github.bonigarcia.wdm.WebDriverManager
import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.slf4j.Logger

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver
import static io.javalin.http.staticfiles.Location.CLASSPATH
import static java.lang.Boolean.valueOf
import static java.lang.System.getenv
import static java.net.InetAddress.getByName
import static org.slf4j.LoggerFactory.getLogger

/**
 * @author David Avenante
 * @since 1.0.0
 */
class TyroWebTestExtension implements BeforeAllCallback, AfterAllCallback {
    private static Logger LOGGER = getLogger(TyroWebTestExtension)

    public static String BASE_URL
    private static WebDriver webDriver
    private static WebDriverManager wdm

    private static Javalin app
    private boolean isCI = valueOf(getenv('CI'))
    private String browser = getenv('browser')?.toLowerCase()

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({it.staticFiles.add "/webapp", CLASSPATH }).start(0)

        DatagramSocket socket = new DatagramSocket()
        socket.connect(getByName("8.8.8.8"), 10002)
        String host_ip = socket.localAddress.hostAddress
        BASE_URL = "http://${host_ip}:${app.port()}/"

        if (!browser) {
            LOGGER.info('No Browser selected. Use Chrome')
            browser = "chrome"
        }

        switch (browser) {
            case "firefox":
                wdm = firefoxdriver()
                FirefoxOptions options = new FirefoxOptions()
                options.addArguments("--start-fullscreen")
                options.addArguments("--start-maximized")
                wdm.capabilities(options)
                break
            case "chrome":
                wdm = chromedriver()
                ChromeOptions options = new ChromeOptions()
                options.addArguments("--start-fullscreen")
                wdm.capabilities(options)
                break
            default:
                throw new IllegalStateException("Fail to set browser: " + browser)
        }

        if (isCI) {
            wdm.browserInDocker()
        }

        webDriver = wdm.create()
        WebBundle.init(webDriver)
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        webDriver.quit()
        wdm.quit()
    }
}
