package sc.tyro.core

import sc.tyro.core.component.Component
import sc.tyro.core.input.MouseModifiers

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
public interface Provider {
    public def <T extends Component> T find(By by, Class<T> clazz)

    public def <T extends Component>  List<T> findAll(By by, Class<T> clazz)

    public def <T extends Component> List<T> findBy(Class<T> clazz)

    public List<MetaInfo> metaInfo(By.ByExpression expression)

    public void click(Component component, Collection<MouseModifiers> mouseModifiers, Collection<?> keys)

    public void mouseOver(Component component)

    public void dragAndDrop(Component from, Component to)

    public void type(Collection<?> keys)

    public boolean enabled(Component component)

    public boolean visible(Component component)

    public boolean contains(Component parent, Component child)

    // Navigation
    public void open(String url)

    public void back()

    public void forward()

    public void refresh()

    public String getPageTitle()

    public String getUrl()

    // Windows
    public void closeWindow(String id)

    public List<String> getWindowIds()

    public void switchToWindow(String windowId)

    // Execution
    public String eval(String id, String expr)

    public Boolean check(String id, String expr)

    public def <T> T getJson(String expression)

    public void runScript(String script)

    public void registerScripts(String... scripts)
}
