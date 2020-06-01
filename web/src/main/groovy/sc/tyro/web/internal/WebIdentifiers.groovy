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
