package sc.tyro.core.internal

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sc.tyro.core.Config

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class Wait {
    private static final Logger logger = LoggerFactory.getLogger(Wait.class)

    static void waitUntil(Closure c, Matcher what = null) {
        boolean success = false
        long timeout = Config.waitUntil.toMillis()
        long interval = 200

        logger.info("WaitUntil: " + timeout)
        for (; timeout > 0; timeout -= interval) {
            try {
                if (what ? what.matches(c.delegate) : c()) {
                    success = true
                    break
                }
            } catch (e) {
                logger.info('Matcher evaluation fail with this exception : ' + e.message)
                logger.info('Retrying...')
            }
            Thread.sleep(interval)
        }

        if (!success) {
            Description description = new StringDescription()
            description.appendText('Unable to reach the condition after ' + Config.waitUntil.toMillis() + ' milliseconds')
            if (what) {
                description.appendText('\nExpected: ')
                        .appendDescriptionOf(what)
                        .appendText('\n     but: ')
                what.describeMismatch(c.delegate, description)
            }
            throw new AssertionError(new RuntimeException(description.toString()))
        }
    }
}
