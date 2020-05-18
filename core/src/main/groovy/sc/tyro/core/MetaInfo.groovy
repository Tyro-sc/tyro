package sc.tyro.core
/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
abstract class MetaInfo {
    String node
    String id

    @Override
    String toString() { "id=${id}, node=${node}" }

    abstract Object asType(Class clazz)
}
