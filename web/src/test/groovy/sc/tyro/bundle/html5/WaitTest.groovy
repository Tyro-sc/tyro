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

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*

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
        visit 'http://localhost:8080/wait.html'
    }

    @AfterAll
    static void after() {
        Config.waitUntil = 2.seconds
    }

    @Test
    void should_be_able_to_wait_on_condition() {
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
    void should_throw_exception_when_condition_in_not_reach_in_expected_duration() {
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
