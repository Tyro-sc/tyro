package sc.tyro.web

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import sc.tyro.core.By
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.component.Component
import sc.tyro.core.input.MouseModifiers

class SeleniumProvider implements Provider {
    private final WebDriver webDriver
    private final JavascriptExecutor js
    private final List<String> registeredScripts = new ArrayList<>()

    SeleniumProvider(WebDriver webDriver) {
        this.webDriver = webDriver
        this.js = (JavascriptExecutor) webDriver
    }

    @Override
    Component find(By by) {
        return null
    }

    @Override
    List findAll(Object By, Class clazz) {
        return null
    }

    @Override
    <T extends Component> List<T> findByType(Class<T> clazz) {
        return null
    }

    @Override
    MetaInfo metaInfo(Component component) {
        return null
    }

    @Override
    void click(Component component, Collection<MouseModifiers> click, Collection<?> keys) {

    }

    @Override
    void mouseOver(Component component) {

    }

    @Override
    void dragAndDrop(Component from, Component to) {

    }

    @Override
    void type(Collection<?> keys) {

    }

    @Override
    void back() {

    }

    @Override
    void forward() {

    }

    @Override
    void refresh() {

    }

    @Override
    String getTitle() {
        return null
    }

    @Override
    String getUrl() {
        return null
    }

    @Override
    void open(String url) {
        webDriver.get(url)
    }

    @Override
    String[] getWindowIds() {
        return new String[0]
    }

    @Override
    void switchToWindow(String windowId) {

    }

    @Override
    boolean enabled(Component component) {
        return false
    }

    @Override
    boolean visible(Component component) {
        return false
    }

    @Override
    boolean contains(Component component) {
        return false
    }

    @Override
    String eval(String id, String expr) {
        return null
    }
}
