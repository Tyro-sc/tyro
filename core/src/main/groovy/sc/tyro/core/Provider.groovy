package sc.tyro.core


import sc.tyro.core.component.Component
import sc.tyro.core.input.MouseModifiers

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
public interface Provider {
    def <T extends Component> T find(By by, Class<T> clazz)

    def <T extends Component>  List<T> findAll(By, Class<T> clazz)

    def <T extends Component> List<T> findByType(Class<T> clazz)

    MetaInfo metaInfo(Component component)

    void click(Component component, Collection<MouseModifiers> click, Collection<?> keys)

    void mouseOver(Component component)

    void dragAndDrop(Component from, Component to)

    void type(Collection<?> keys)

    void navigateTo(String url)

    void back()

    void forward()

    void refresh()

    String getTitle()

    String getUrl()

    void open(String url)

    String[] getWindowIds()

    void switchToWindow(String windowId)

    boolean enabled(Component component)

    boolean visible(Component component)

    boolean contains(Component component)

    abstract String eval(String id, String expr)
}