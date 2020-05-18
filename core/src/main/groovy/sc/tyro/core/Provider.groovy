package sc.tyro.core


import sc.tyro.core.component.Component
import sc.tyro.core.input.MouseModifiers

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
public interface Provider {
    MetaDataProvider getMetaDataProvider()

    def <T extends Component> T find(By by, Class<T> clazz)

    def <T extends Component>  List<T> findAll(By, Class<T> clazz)

    def <T extends Component> List<T> findBy(Class<T> clazz)

    MetaInfo metaInfo(Component component)

    void click(Component component, Collection<MouseModifiers> click, Collection<?> keys)

    void mouseOver(Component component)

    void dragAndDrop(Component from, Component to)

    void type(Collection<?> keys)

    boolean enabled(Component component)

    boolean visible(Component component)

    boolean contains(Component component)

    // Navigation
    void open(String url)

    void back()

    void forward()

    void refresh()

    String getPageTitle()

    String getUrl()

    // Windows
    void closeWindow(String id)

    List<String> getWindowIds()

    void switchToWindow(String windowId)

    abstract String eval(String id, String expr)
}