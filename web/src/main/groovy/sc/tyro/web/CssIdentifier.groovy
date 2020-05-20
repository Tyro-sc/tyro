package sc.tyro.web

import sc.tyro.core.Identifier

import java.lang.annotation.*

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Identifier
@interface CssIdentifier {
    String value()
}
