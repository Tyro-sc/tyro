package sc.tyro.core

import com.google.common.reflect.ClassPath
import sc.tyro.core.component.Component

import java.time.Duration

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Config {
    /**
     * Change default time to wait for wait for assertions to complete (waitUntil)
     */
    static Duration waitUntil = 2.seconds

    static Provider provider

    static MetaDataProvider meta

    static Identifiers identifiers

    final static Collection<Class<Component>> componentTypes = new HashSet<>()

    static void scan(String... packageNames) {
        componentTypes.addAll(packageNames
                .collect { ClassPath.from(Thread.currentThread().contextClassLoader).getTopLevelClassesRecursive(it) }
                .flatten()
                .collect { it.load() }
                .findAll { Component.isAssignableFrom(it) && identifiers.hasIdentifier(it) })
    }
}
