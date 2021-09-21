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
package sc.tyro.core.internal

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sc.tyro.core.Config

import java.time.Duration

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class Wait {
    private static final Logger LOGGER = LoggerFactory.getLogger(Wait)

    static void waitUntil(Closure c, Duration duration) {
        waitUntil(c, null, duration)
    }

    static void waitUntil(Closure c, Matcher what, Duration duration) {
        boolean success = false
        long timeout = (duration != null) ? duration.toMillis() : Config.waitUntil.toMillis()
        long interval = 200

        LOGGER.debug("WaitUntil: " + timeout)
        for (; timeout > 0; timeout -= interval) {
            try {
                if (what ? what.matches(c.delegate) : c()) {
                    success = true
                    break
                }
            } catch (e) {
                LOGGER.debug('Matcher evaluation fail with this exception : ' + e.message)
                LOGGER.debug('Retrying...')
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
            throw new AssertionError(description)
        }
    }
}
