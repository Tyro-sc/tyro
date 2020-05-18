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
    Provider provider = Config.provider
    MetaDataProvider metaDataProvider

    Component() {}

    public String id() {
        metaDataProvider.metaInfo(this).id
    }

    public boolean enabled() {
        provider.enabled(this)
    }

    public boolean available() {
        try {
            metaDataProvider.metaInfo(this)
            return true
        } catch (ComponentException ignored) {
            return false
        }
    }

    public boolean visible() {
        provider.visible(this)
    }

    public boolean contains(Component component) {
        provider.contains(component)
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

    def asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c.metaDataProvider = this.metaDataProvider
            return c
        }
        // TODO: better to throw an Exception
        // Fallback to default
        return super.asType(clazz)
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

    void setProvider(Provider provider) {
        this.provider = provider
    }
//
//    Provider getProvider() {
//        provider
//    }
}