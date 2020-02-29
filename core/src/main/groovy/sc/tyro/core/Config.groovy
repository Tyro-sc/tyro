package sc.tyro.core

import java.time.Duration

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Config {
    /**
     * Change default time to wait for wait for assertions to complete (waitUntil)
     */
    public static Duration waitUntil = 2.seconds

    public static Provider provider
}
