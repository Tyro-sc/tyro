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
package sc.tyro.web.internal

import sc.tyro.core.ComponentException
import sc.tyro.core.Identifiers
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier
import sc.tyro.core.Identifier

import java.lang.annotation.Annotation

class WebIdentifiers implements Identifiers {
    static Map factories = [
            (CssIdentifier): { CssIdentifier annotation -> return "it.is('${annotation.value()}')" }
    ]

    boolean hasIdentifier(Class<? extends Component> c) {
        return c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
    }

    static String identifyingExpression(Class<? extends Component> c) {
        Annotation annotation = c.declaredAnnotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        if (!annotation) {
            annotation = c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        }
        if (!annotation) {
            throw new ComponentException("Missing @Identifier annotation on type " + c.name)
        }
        Closure<String> handler = factories[annotation.annotationType()]
        if (!handler) {
            throw new ComponentException("Missing handler for annotation type " + annotation.annotationType().name)
        }
        return handler.call(annotation)
    }
}
