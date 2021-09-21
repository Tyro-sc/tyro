/*
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
package sc.tyro.bundle.html5

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.Config
import sc.tyro.web.TyroWebTestExtension

import static java.time.Duration.ofSeconds
import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Wait Tests")
class WaitTest {
    @BeforeAll
    static void before() {
        Config.waitUntil = 10.seconds
        visit BASE_URL + 'wait.html'
    }

    @AfterAll
    static void after() {
        Config.waitUntil = 2.seconds
    }

    @Test
    @DisplayName("Should be able to wait on condition")
    void waitOnCondition() {
        browser().refresh()

        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        button.should {
            be enabled
            be visible
        }

        message.should { be missing }

        clickOn button
        button.should { be disabled }
        button.should { be enabled }

        clickOn button
        button.should { be enabled }
    }

    @Test
    @DisplayName("Should be able to wait xxplicitly on condition")
    void waitExplicitlyOnCondition() {
        browser().refresh()

        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        waitUntil({ !message.available() }, ofSeconds(1))

        clickOn button

        waitUntil({ message.available() }, ofSeconds(5))
    }

    @Test
    @DisplayName("Should fail when condition not reached in expected duration")
    void expectedDuration() {
        browser().refresh()

        Button button = $('#add-message') as Button
        Description expectedMessage = new StringDescription()
        expectedMessage.appendText('Unable to reach the condition after 10000 milliseconds')
                .appendText('\nExpected: is disabled')
                .appendText('\n     but: is enabled')

        AssertionError error = assertThrows(AssertionError, { button.should { be disabled } })
        assert error.message == expectedMessage.toString()
    }
}
