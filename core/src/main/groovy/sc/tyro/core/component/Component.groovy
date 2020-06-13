package sc.tyro.core.component

import org.hamcrest.Matcher
import sc.tyro.core.ComponentException
import sc.tyro.core.Config
import sc.tyro.core.MetaDataProvider
import sc.tyro.core.Provider
import sc.tyro.core.input.DragBuilder
import sc.tyro.core.input.MouseModifiers
import sc.tyro.core.support.Draggable
import sc.tyro.core.support.MouseSupport

import static java.util.Collections.unmodifiableCollection
import static sc.tyro.core.input.MouseModifiers.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Component implements MouseSupport, Draggable {
    private final Queue<Matcher> BLOCKS = new LinkedList<>()
    protected Provider provider
    protected MetaDataProvider meta

    Component() {
        this.provider = Config.provider
        this.meta = Config.meta
    }

    Component(Provider provider, MetaDataProvider meta) {
        this.provider = provider
        this.meta = meta
    }

    String id() {
        meta.metaInfo(this).id
    }

    boolean enabled() {
        provider.enabled(this)
    }

    boolean available() {
        try {
            meta.metaInfo(this)
            return true
        } catch (ComponentException ignored) {
            return false
        }
    }

    boolean visible() {
        provider.visible(this)
    }

    boolean contains(Component component) {
        provider.contains(this, component)
    }

    @Override
    void click() {
        provider.click(this, [LEFT, SINGLE] as Collection<MouseModifiers>, [])
    }

    @Override
    void doubleClick() {
        provider.click(this, [LEFT, DOUBLE] as Collection<MouseModifiers>, [])
    }

    @Override
    void mouseOver() {
        provider.mouseOver(this)
    }

    @Override
    void rightClick() {
        provider.click(this, [RIGHT, SINGLE] as Collection<MouseModifiers>, [])
    }

    @Override
    DragBuilder drag() {
        new DragBuilder(this)
    }

    Provider getProvider() {
        return provider
    }

    @Override
    boolean equals(Object o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false
        id() == ((Component) o).id()
    }

    @Override
    int hashCode() {
        id().hashCode()
    }

    @Override
    String toString() {
        getClass().simpleName + ":${id()}"
    }

    def asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c.provider = provider
            c.meta = meta
            return c
        }
        throw new IllegalStateException("Unable to assign instance to " + clazz)
    }

    Collection<Matcher> getBlocks() {
        unmodifiableCollection(BLOCKS)
    }

    boolean addBlock(Matcher matcher) {
        BLOCKS.add(matcher)
    }

    void clearBlocks() {
        BLOCKS.clear()
    }
}