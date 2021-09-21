/*
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.core.internal

import org.hamcrest.Matcher
import sc.tyro.core.ComponentException
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

    static PropertyMatcher getDisabledItems(Integer number) {
        new DisabledItemsSizeMatcher(number)
    }

    static PropertyMatcher getSelectedItems(Integer number) {
        new SelectedItemsSizeMatcher(number)
    }

    static PropertyMatcher getUnSelectedItems(Integer number) {
        new UnSelectedItemsSizeMatcher(number)
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
        component.addBlock(is(matcher.getDeclaredConstructor().newInstance()))
    }

    static void contain(Component component, Component... components) {
        component.addBlock(new ContainMatcher(components))
    }

    static void have(Component component, PropertyMatcher matcher) {
        component.addBlock(has((matcher)))
    }
}
