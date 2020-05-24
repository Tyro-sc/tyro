package sc.tyro.core

import sc.tyro.core.component.Component

import static sc.tyro.core.Config.provider

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class MetaInfo {
    String node
    String id

    @Override
    String toString() { "id=${id}, node=${node}" }

    // TODO: remove
    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            return provider.find(By.id(id), clazz)
        }
        return super.asType(clazz)
    }
}
