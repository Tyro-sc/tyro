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
public class Component implements MouseSupport, Draggable {
    private final Queue<Matcher> BLOCKS = new LinkedList<>()
    protected Provider provider
    protected MetaDataProvider meta

    public Component() {
        this.provider = Config.provider
        this.meta = null
    }

    public Component(Provider provider, MetaDataProvider meta) {
        this.provider = provider
        this.meta = meta
    }

    public String id() {
        meta.metaInfo(this).id
    }

    public boolean enabled() {
        provider.enabled(this)
    }

    public boolean available() {
        try {
            meta.metaInfo(this)
            return true
        } catch (ComponentException ignored) {
            return false
        }
    }

    public boolean visible() {
        provider.visible(this)
    }

    public boolean contains(Component component) {
        provider.contains(this, component)
    }

    @Override
    public void click() {
        provider.click(this, [LEFT, SINGLE] as Collection<MouseModifiers>, [])
    }

    @Override
    public void doubleClick() {
        provider.click(this, [LEFT, DOUBLE] as Collection<MouseModifiers>, [])
    }

    @Override
    public void mouseOver() {
        provider.mouseOver(this)
    }

    @Override
    public void rightClick() {
        provider.click(this, [RIGHT, SINGLE] as Collection<MouseModifiers>, [])
    }

    @Override
    public DragBuilder drag() {
        new DragBuilder(this)
    }

    public Provider getProvider() {
        return provider
    }

    @Override
    public boolean equals(Object o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false
        Component component = (Component) o
        id() == component.id()
    }

    @Override
    public int hashCode() {
        id().hashCode()
    }

    @Override
    public String toString() {
        getClass().simpleName + ":${id()}"
    }

    public def asType(Class clazz) {
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