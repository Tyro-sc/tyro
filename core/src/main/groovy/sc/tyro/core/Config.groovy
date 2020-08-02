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
