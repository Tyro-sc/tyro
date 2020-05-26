package sc.tyro.web

import groovy.json.JsonSlurper
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sc.tyro.core.By
import sc.tyro.core.MetaDataProvider
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.component.Component
import sc.tyro.core.input.Key
import sc.tyro.core.input.MouseModifiers
import sc.tyro.web.internal.CachedMetaData
import sc.tyro.web.internal.DomIdProvider

import static sc.tyro.core.input.MouseModifiers.*
import static sc.tyro.web.KeyConverter.convert

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class SeleniumProvider implements Provider {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumProvider)
    private final List<String> registeredScripts = new ArrayList<>()

    private final WebDriver webDriver
    private final JavascriptExecutor js

    SeleniumProvider(WebDriver webDriver) {
        this.webDriver = webDriver
        this.js = (JavascriptExecutor) webDriver
    }

    @Override
    <T extends Component> T find(By by, Class<T> clazz) {
        (T) new Component(this, new CachedMetaData(idProvider: new DomIdProvider(convertToExpression(by), true))).asType(clazz)
    }

    @Override
    <T extends Component> List<T> findBy(Class<T> clazz) {
//        TODO: factories
        return null
    }

    @Override
    List findAll(By by, Class clazz) {
        Components components = new Components(this, clazz, new CachedMetaData(idProvider: new DomIdProvider(convertToExpression(by), false)))
        components.list()
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
    void click(Component component, Collection<MouseModifiers> mouseModifiers, Collection<?> keys) {
        WebElement element = webDriver.findElement(org.openqa.selenium.By.id(component.id()))
        // https://github.com/mozilla/geckodriver/issues/776
        runScript("document.getElementById('${component.id()}').scrollIntoView(true)")

        // Temporary hack until Selenium fix
        if (optionInDropdown(component.id())) {
            element.click()
            return
        }

        Collection<Key> modifiers = []
        Collection<String> text = []
        keys.each { k ->
            if (k instanceof Key && text) throw new IllegalArgumentException('Cannot type a modifier after some text')
            if (k instanceof Key && k in [Key.SHIFT, Key.CTRL, Key.ALT]) modifiers << k
            else text << k as String
        }

        Actions action = new Actions(webDriver)
        modifiers.each { action.keyDown(convert(it)) }
        text.each { it instanceof Key ? action.sendKeys(convert(it)) : action.sendKeys(it) }
        if (mouseModifiers == [LEFT, SINGLE]) {
            action.click(element)
        } else if (mouseModifiers == [RIGHT, SINGLE]) {
            action.contextClick(element)
        } else if (mouseModifiers == [LEFT, DOUBLE]) {
            action.doubleClick(element)
        } else {
            throw new IllegalArgumentException('Invalid click sequence')
        }
        modifiers.each { action.keyUp(convert(it)) }
        action.perform()
    }

    @Override
    void mouseOver(Component component) {
        new Actions(webDriver).moveToElement(webDriver.findElement(org.openqa.selenium.By.id(component.id()))).build().perform()
    }

    @Override
    void dragAndDrop(Component from, Component to) {
        new Actions(webDriver).dragAndDrop(webDriver.findElement(org.openqa.selenium.By.id(from.id())), webDriver.findElement(org.openqa.selenium.By.id(to.id()))).perform()
    }

    @Override
    void type(Collection<?> keys) {
        Collection<Key> modifiers = []
        Collection<String> text = []
        keys.each { k ->
            if (k instanceof Key && text) throw new IllegalArgumentException('Cannot type a modifier after some text')
            if (k instanceof Key && k in [Key.SHIFT, Key.CTRL, Key.ALT]) modifiers << k
            else text << k as String
        }

        Actions action = new Actions(webDriver)
        modifiers.each { action.keyDown(convert(it)) }
        text.each {
            it instanceof Key ? action.sendKeys(convert(it)) : action.sendKeys(it)
        }
        modifiers.each { action.keyUp(convert(it)) }
        action.build().perform()
    }

    @Override
    void back() {
        webDriver.navigate().back()
    }

    @Override
    void forward() {
        webDriver.navigate().forward()
    }

    @Override
    void refresh() {
        webDriver.navigate().refresh()
    }

    @Override
    String getPageTitle() {
        webDriver.title
    }

    @Override
    String getUrl() {
        webDriver.currentUrl
    }

    @Override
    void closeWindow(String id) {
        webDriver.windowHandles
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
        // TODO switch window
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
    boolean contains(Component parent, Component child) {
        Integer.valueOf(eval(parent.id(), "it.has('#${child.id()}').length")) > 0
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

    @Override
    void runScript(String script) {
        js.executeScript(script)
    }

    @Override
    void registerScripts(String... scripts) {
        registeredScripts.addAll(scripts)
    }

    private static By.ByExpression convertToExpression(By by) {
        switch (by.class) {
            case By.ByExpression:
                return by as By.ByExpression
            case By.ById:
                return By.expression("#" + ((By.ById) by).id)
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

    private boolean optionInDropdown(String id) {
        return check(id, "it.prop('tagName').toLowerCase() === 'option' && !it.closest('select').prop('multiple')")
    }

    static class Components<T extends Component> {
        private final Provider provider
        private final MetaDataProvider meta
        private final Class<T> type
        private List<T> components

        Components(Provider provider, Class<T> type, MetaDataProvider meta) {
            this.provider = provider
            this.meta = meta
            this.type = type
        }

        List<T> list() {
            if (components == null) {
                components = meta.metaInfos().collect {
                    new Component(provider, new CachedMetaData(idProvider: new DomIdProvider(By.expression('[id="' + it.id + '"]'), false))).asType(type)
                } as List<T>
            }
            return Collections.unmodifiableList(components)
        }
    }
}
