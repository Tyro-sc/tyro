package sc.tyro.web.internal

import io.github.classgraph.ClassGraph
import io.github.classgraph.ScanResult
import sc.tyro.core.ComponentException
import sc.tyro.core.Config
import sc.tyro.core.Identifiers
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier
import sc.tyro.web.Identifier

import java.lang.annotation.Annotation

public class WebIdentifiers implements Identifiers {
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

//    private static Map<Class, List<Class>> cachedComponents = new HashMap<>()

//    private static ScanResult scan = new ClassGraph()
//            .whitelistPackages('org.testatoo.bundle')
//            .scan()



//
//    static Map<Class, String> findSelectorsFor(Class clazz) {
//        Map<Class, String> selectors = new HashMap<>()
//
//        if (!cachedComponents.get(clazz)) {
//            cachedComponents.put(clazz, scan.getSubclasses(clazz.name).loadClasses())
//        }
//
//        cachedComponents.get(clazz).each {
//            Annotation annotation = it.declaredAnnotations.find { it.annotationType().isAnnotationPresent(Identifier) }
//            if (annotation == null) {
//                throw new ComponentException("Unable to find any component definition for: " + clazz)
//            }
//            selectors.put(it, annotation.value())
//        }
//        selectors
//    }
}
