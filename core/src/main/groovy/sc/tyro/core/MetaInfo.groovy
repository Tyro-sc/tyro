package sc.tyro.core

import groovy.transform.Immutable
import sc.tyro.core.component.Component

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
@Immutable
class MetaInfo {
    String node
    String id

    @Override
    String toString() { "id=${id}, node=${node}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
//            return $("[id=\"${id}\"]").asType(clazz)
        }
        return super.asType(clazz)
    }
}
