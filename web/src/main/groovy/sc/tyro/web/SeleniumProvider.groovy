package sc.tyro.web

import groovy.json.JsonSlurper
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sc.tyro.core.By
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.component.Component
import sc.tyro.core.input.MouseModifiers
import sc.tyro.web.internal.CachedMetaData
import sc.tyro.web.internal.DomIdProvider

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class SeleniumProvider implements Provider {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumProvider)

    private final WebDriver webDriver
    private final JavascriptExecutor js
    private final List<String> registeredScripts = new ArrayList<>()

    SeleniumProvider(WebDriver webDriver) {
        this.webDriver = webDriver
        this.js = (JavascriptExecutor) webDriver
    }

    @Override
    <T extends Component> T find(By by, Class<T> clazz) {
        new Component(metaDataProvider: new CachedMetaData(idProvider: new DomIdProvider(convertToExpression(by), true))).asType(clazz)
    }

    @Override
    <T extends Component> List<T> findBy(Class<T> clazz) {
        return null
    }

    @Override
    List findAll(Object By, Class clazz) {
        return null
    }

    @Override
    List<MetaInfo> metaInfo(By.ByExpression expression) {
        List<Map> infos = getJson("${removeTrailingChars(expression.expression)}.tyro({method: 'metaInfos'});")
        return infos.collect {
            new MetaInfo(
                    id: it.id,
                    node: it.node
            )
        }
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
    String getPageTitle() {
        return null
    }

    @Override
    String getUrl() {
        return null
    }

    @Override
    void closeWindow(String id) {

    }

    @Override
    void open(String url) {
        webDriver.get(url)
    }

    @Override
    List getWindowIds() {
        return List.of()
    }

    @Override
    void switchToWindow(String windowId) {

    }

    @Override
    boolean enabled(Component component) {
        !check(component.id(), "it.is(':disabled') || !!it.attr('disabled')")
    }

    @Override
    boolean visible(Component component) {
        !check(component.id(), "it.is(':hidden')")
    }

    @Override
    boolean contains(Component component) {
        return false
    }

    @Override
    String eval(String id, String expr) {
        execute(id, expr)
    }

    @Override
    Boolean check(String id, String expr) {
        Boolean.parseBoolean(eval(id, expr))
    }

    @Override
    <T> T getJson(String expression) {
        eval(null, "JSON.stringify(${removeTrailingChars(expression)})")?.with { new JsonSlurper().parseText(it) as T }
    }

    private static By.ByExpression convertToExpression(By by) {
        switch (by.class) {
            case By.ByExpression:
                return by as By.ByExpression
            default:
                throw new RuntimeException("Invalid By.xxx")
        }
    }

    private String execute(String id, String expression) {
        String element = ''
        if (id) {
            element = "var it = el = \$('[id=\"${id}\"]');"
        }

        String expr = """
        return (function(jQuery) {
            if(!jQuery) { return '__JQUERY_MISSING__'; }
            if (!jQuery().tyro) { return '__TYRO_MISSING__'; }
            else {
                $element
                return ${removeTrailingChars(expression)};
            }
        }(window.jQuery));"""

        LOGGER.debug('====== Expression ======')
        LOGGER.debug(expr)

        String v = js.executeScript(expr)

        LOGGER.debug('======== RESULT ========')
        LOGGER.debug(v)

        if (v == '__JQUERY_MISSING__') {
            js.executeScript(getClass().getResource('jquery-3.1.1.slim.min.js').text
                    + getClass().getResource('tyro.js').text)
            registeredScripts.each { js.executeScript(it) }
            return execute(id, expression)
        }
        if (v == '__TYRO_MISSING__') {
            js.executeScript(getClass().getResource('tyro.js').text)
            registeredScripts.each { js.executeScript(it) }
            return execute(id, expression)
        }

        return v == 'null' || v == 'undefined' ? null : v
    }

    private static String removeTrailingChars(String expr) {
        expr = expr.trim()
        expr.endsWith(';') ? expr.substring(0, expr.length() - 1) : expr
    }
}
