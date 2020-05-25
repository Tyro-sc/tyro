package sc.tyro.core.internal

import org.hamcrest.Matcher
import sc.tyro.core.ComponentException
import sc.tyro.core.Config
import sc.tyro.core.Tyro
import sc.tyro.core.component.Component
import sc.tyro.core.component.Item
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.hamcrest.StateMatcher
import sc.tyro.core.hamcrest.matcher.property.*
import sc.tyro.core.hamcrest.matcher.state.ContainMatcher
import sc.tyro.core.input.Key
import sc.tyro.core.input.MouseModifiers
import sc.tyro.core.support.Selectable
import sc.tyro.core.support.UnSelectable

import java.time.Duration

import static java.time.Duration.ofSeconds
import static org.hamcrest.Matchers.is
import static sc.tyro.core.Tyro.waitUntil
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.input.MouseModifiers.*

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class GroovyExtensions {
    static Duration getSeconds(Number self) { ofSeconds(self.longValue()) }

    static Collection<?> plus(Key a, Key b) { [a, b] }

    static Collection<?> plus(Key a, String b) { [a, b] }

    static void click(Key key, Component c) { click([key], c) }

    static void click(Collection<Key> keys, Component c) {
        c.provider.click(c, [LEFT, SINGLE] as Collection<MouseModifiers>, keys)
    }

    static void rightClick(Key key, Component c) { rightClick([key], c) }

    static void rightClick(Collection<Key> keys, Component c) {
        c.provider.click(c, [RIGHT, SINGLE] as Collection<MouseModifiers>, keys)
    }

    // ====================================================================

    static void select(Selectable component, String... values) {
        values.each { value ->
            component.items().find { it.value() == value }.each { select(component, (Item) it) }
        }
    }

    static void select(Selectable component, Item... items) {
        items.each { item ->
            if (component.items().contains(item)) {
                Tyro.select(item)
            } else {
                throw new ComponentException("${item.class.simpleName} ${item} is not contains by ${component.class.simpleName} ${component}")
            }
        }
    }

    static void unselect(UnSelectable component, String... values) {
        values.each { value ->
            component.items().find { it.value() == value }.each { unselect(component, (Item) it) }
        }
    }

    static void unselect(UnSelectable component, Item... items) {
        items.each { item ->
            if (component.items().contains(item)) {
                Tyro.unselect(item)
            } else {
                throw new ComponentException("${item.class.simpleName} ${item} is not contains by ${component.class.simpleName} ${component}")
            }
        }
    }

    // ====================================================================

    static PropertyMatcher getItems(Integer number) {
        new ItemSizeMatcher(number)
    }

    static PropertyMatcher getVisibleItems(Integer number) {
        new VisibleItemsSizeMatcher(number)
    }

    static PropertyMatcher getGroups(Integer number) {
        new GroupSizeMatcher(number)
    }

    static PropertyMatcher getColumns(Integer number) {
        new ColumnSizeMatcher(number)
    }

    static PropertyMatcher getRows(Integer number) {
        new RowSizeMatcher(number)
    }

    static PropertyMatcher getCells(Integer number) {
        new CellSizeMatcher(number)
    }

    static void should(Component component, Closure closure) {
        closure.delegate = component
        closure(this)
        for (Matcher matcher : component.blocks) {
            waitUntil(closure, matcher)
        }
        component.clearBlocks()
    }

    static void be(Component component, Class<StateMatcher> matcher) {
        component.addBlock(is(matcher.newInstance()))
    }

    static void contain(Component component, Component... components) {
        component.addBlock(new ContainMatcher(components))
    }

    static void have(Component component, PropertyMatcher matcher) {
        component.addBlock(has((matcher)))
    }
}
