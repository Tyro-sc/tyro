package sc.tyro.bundle.html5

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.Config
import sc.tyro.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@Disabled
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
