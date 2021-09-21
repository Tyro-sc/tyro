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
package sc.tyro.core

import org.hamcrest.Matcher
import sc.tyro.core.component.*
import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.component.datagrid.Row
import sc.tyro.core.component.field.Field
import sc.tyro.core.hamcrest.matcher.property.*
import sc.tyro.core.hamcrest.matcher.state.*
import sc.tyro.core.input.DragBuilder
import sc.tyro.core.input.Key
import sc.tyro.core.input.Keyboard
import sc.tyro.core.input.Mouse
import sc.tyro.core.internal.Wait
import sc.tyro.core.support.*
import sc.tyro.core.support.property.*

import java.time.Duration

import static sc.tyro.core.By.expression
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Config.screenshotProvider
import static sc.tyro.core.input.Key.COMMAND
import static sc.tyro.core.input.Key.CTRL

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Tyro {
    static Component $(String expression) {
        provider.find(Component, By.expression(expression))
    }

    static <T extends Component> List<T> $$(String expression, Class<T> clazz = Component) {
        provider.findAll(clazz, By.expression(expression))
    }

    static <T extends Component> List<T> findAll(Class<T> clazz = Component) {
        provider.findAll(clazz)
    }

    static mouse = new Mouse()
    static keyboard = new Keyboard()

    /**
     * States
     */
    static Class available = AvailableMatcher
    static Class checked = CheckedMatcher
    static Class disabled = DisabledMatcher
    static Class empty = EmptyMatcher
    static Class enabled = EnabledMatcher
    static Class filled = FilledMatcher
    static Class hidden = HiddenMatcher
    static Class inRange = InRangeMatcher
    static Class invalid = InvalidMatcher
    static Class missing = MissingMatcher
    static Class optional = OptionalMatcher
    static Class outOfRange = OutOfRangeMatcher
    static Class readOnly = ReadOnlyMatcher
    static Class required = RequiredMatcher
    static Class selected = SelectedMatcher
    static Class unchecked = UnCheckedMatcher
    static Class unselected = UnSelectedMatcher
    static Class valid = ValidMatcher
    static Class visible = VisibleMatcher
    static Class focused = FocusedMatcher
    static Class expanded = ExpandedMatcher
    static Class collapsed = CollapsedMatcher
    static Class on = SwitchedOnMatcher
    static Class off = SwitchedOffMatcher

    /**
     * Properties
     */
    static LabelMatcher label(String label) { new LabelMatcher(label) }

    static MaximumMatcher maximum(object) { new MaximumMatcher(object) }

    static MinimumMatcher minimum(object) { new MinimumMatcher(object) }

    static PlaceholderMatcher placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }

    static LengthMatcher length(object) { new LengthMatcher(object) }

    static ItemMatcher items(String... values) { new ItemMatcher(values) }

    static ItemMatcher items(Item... items) { new ItemMatcher(items) }

    static ColumnMatcher columns(String... values) { new ColumnMatcher(values) }

    static ColumnMatcher columns(Column... columns) { new ColumnMatcher(columns) }

    static RowMatcher rows(String... values) { new RowMatcher(values) }

    static RowMatcher rows(Row... rows) { new RowMatcher(rows) }

    static CellMatcher cells(String... values) { new CellMatcher(values) }

    static CellMatcher cells(Cell... cells) { new CellMatcher(cells) }

    static GroupMatcher groups(String... values) { new GroupMatcher(values) }

    static GroupMatcher groups(Group... groups) { new GroupMatcher(groups) }

    static SelectedItemMatcher selectedItem(String item) { new SelectedItemMatcher(item) }

    static SelectedItemMatcher selectedItem(Item item) { new SelectedItemMatcher(item) }

    static SelectedItemsMatcher selectedItems(String... items) { new SelectedItemsMatcher(items) }

    static SelectedItemsMatcher selectedItems(Item... items) { new SelectedItemsMatcher(items) }

    static UnSelectedItemMatcher unSelectedItem(String value) { new UnSelectedItemMatcher(value) }

    static UnSelectedItemMatcher unSelectedItem(Item value) { new UnSelectedItemMatcher(value) }

    static UnSelectedItemsMatcher unSelectedItems(String... values) { new UnSelectedItemsMatcher(values) }

    static UnSelectedItemsMatcher unSelectedItems(Item... values) { new UnSelectedItemsMatcher(values) }

    static DisabledItemMatcher disabledItem(String item) { new DisabledItemMatcher(item) }

    static DisabledItemMatcher disabledItem(Item item) { new DisabledItemMatcher(item) }

    static DisabledItemsMatcher disabledItems(String... items) { new DisabledItemsMatcher(items) }

    static DisabledItemsMatcher disabledItems(Item... items) { new DisabledItemsMatcher(items) }

    static VisibleItemMatcher visibleItem(String item) { new VisibleItemMatcher(item) }

    static VisibleItemMatcher visibleItem(Item item) { new VisibleItemMatcher(item) }

    static VisibleItemsMatcher visibleItems(String... items) { new VisibleItemsMatcher(items) }

    static VisibleItemsMatcher visibleItems(Item... items) { new VisibleItemsMatcher(items) }

    static StepMatcher step(object) { new StepMatcher(object) }

    static TextMatcher text(String text) { new TextMatcher(text) }

    static ValueMatcher value(Object value) { new ValueMatcher(value) }

    static ReferenceMatcher reference(String reference) { new ReferenceMatcher(reference) }

    static TitleMatcher title(String title) { new TitleMatcher(title) }

    static ErrorMessageMatcher errorMessage(String message) { new ErrorMessageMatcher(message) }

    /**
     * Actions
     */
    static void visit(String uri) { browser().open(uri) }

    static void takeScreenshot(String name, Component component = null) { screenshotProvider.takeScreenshot(name, component) }

    static void check(Checkable... checkables) {
        checkables.each {
            if (!it.enabled())
                throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be checked")
            if (it.checked())
                throw new ComponentException("${it.class.simpleName} ${it} is already checked and cannot be checked")
            it.click()
        }
    }

    static void uncheck(UnCheckable... unCheckables) {
        unCheckables.each {
            if (!it.enabled())
                throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be unchecked")
            if (!it.checked())
                throw new ComponentException("${it.class.simpleName} ${it} is already unchecked and cannot be unchecked")
            it.click()
        }
    }

    static void switchOn(Switchable... switchables) {
        switchables.each {
            if (!it.enabled())
                throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be switched on")
            if (it.on())
                throw new ComponentException("${it.class.simpleName} ${it} is already switched on and cannot be switched on")
            it.click()
        }
    }

    static void switchOff(UnSwitchable... unSwitchables) {
        unSwitchables.each {
            if (!it.enabled())
                throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be switched off")
            if (!it.on())
                throw new ComponentException("${it.class.simpleName} ${it} is already switched off and cannot be switched off")
            it.click()
        }
    }

    static void select(Item... items) {
        items.each { item ->
            if (!item.enabled())
                throw new ComponentException("${item.class.simpleName} ${item} is disabled and cannot be selected")
            if (item.selected()) {
                throw new ComponentException("${item.class.simpleName} ${item} is already selected and cannot be selected")
            }
            withOsModifierClickOn(item)
        }
    }

    static void unselect(Item... items) {
        items.each { item ->
            if (!item.enabled())
                throw new ComponentException("${item.class.simpleName} ${item} is disabled and cannot be deselected")
            if (!item.selected()) {
                throw new ComponentException("${item.class.simpleName} ${item} is already unselected and cannot be deselected")
            }
            withOsModifierClickOn(item)
        }
    }

    static void clear(Clearable c) { c.clear() }

    static void reset(Resettable c) { c.reset() }

    static void submit(Submissible c) { c.submit() }

    static <T extends Component> T on(Component c) { c as T }

    static final FillAction fill(Field c) { new FillAction(c) }

    static final FillAction set(Field c) { new FillAction(c) }

    // Delegate to Mouse
    static void clickOn(Component c) { mouse.clickOn(c) }

    static void doubleClickOn(Component c) { mouse.doubleClickOn(c) }

    static void rightClickOn(Component c) { mouse.rightClickOn(c) }

    static void hoveringMouseOn(Component c) { mouse.hoveringMouseOn(c) }

    static DragBuilder drag(Component c) { mouse.drag(c) }

    // Delegate to Keyboard
    static void type(Collection<?> keys) { keyboard.type(keys) }

    static void type(Key key) { type([key]) }

    static void type(String text) { type([text]) }

    // Generic Component Factory
    static Browser browser() { new Browser(provider) }

    static Button button(String text) { findByText(text, Button) }

    static Radio radio(String label) { findByLabel(label, Radio) }

    static <T extends Field> T field(String label, Class<T> clazz = Field) { findByLabel(label, clazz) }

    static CheckBox checkbox(String label) { findByLabel(label, CheckBox) }

    static Dropdown dropdown(String label) { findByLabel(label, Dropdown) }

    static ListBox listBox(String label) { findByLabel(label, ListBox) }

    static Group group(String value) { findByValue(value, Group) }

    static Item item(String value) { findByValue(value, Item) }

    static Heading heading(String text) { findByText(text, Heading) }

    static Panel panel(String title) { findByTitle(title, Panel) }

    static Link link(String text) { findByText(text, Link) }

    static void waitUntil(Closure c, Matcher what, Duration duration = null) { Wait.waitUntil(c, what, duration) }

    static void waitUntil(Closure c, Duration duration = null) { Wait.waitUntil(c, duration) }

    private static class FillAction {
        private Field input

        FillAction(Field input) {
            this.input = input
        }

        void with(Object value) {
            input.value(value)
        }

        void to(Object value) {
            input.value(value)
        }
    }

    static <T extends Component> T findByLabel(String label, Class<T> clazz) {
        boolean hasPlaceholderSupport = PlaceholderSupport.isAssignableFrom(clazz)
        Collection<T> components = provider.findAll(clazz).findAll {
            (LabelSupport.isAssignableFrom(clazz) ? it.label() == label : false) || (hasPlaceholderSupport ? it.placeholder() == label : false)
        }
        createComponent(components, clazz, label, "label${hasPlaceholderSupport ? ' or placeholder' : ''}")
    }

    static <T extends Component> T findByText(String text, Class<T> clazz) {
        Collection<T> components = provider.findAll(clazz).findAll { (TextSupport.isAssignableFrom(clazz) ? it.text() == text : false) }
        createComponent(components, clazz, text, 'text')
    }

    static <T extends Component> T findByValue(String value, Class<T> clazz) {
        Collection<T> components = provider.findAll(clazz).findAll { (ValueSupport.isAssignableFrom(clazz) ? it.value() == value : false) }
        createComponent(components, clazz, value, 'value')
    }

    static <T extends Component> T findByTitle(String title, Class<T> clazz) {
        Collection<T> components = provider.findAll(clazz).findAll {
            (TitleSupport.isAssignableFrom(clazz) ? it.title() == title : false)
        }
        createComponent(components, clazz, title, 'title')
    }

    static withOsModifierClickOn(Component c) {
        if (System.getProperty("os.name").startsWith("Mac"))
            COMMAND.click c
        else
            CTRL.click c
    }

    static withOsModifierType(String text) {
        if (System.getProperty("os.name").startsWith("Mac"))
            type(COMMAND + text)
        else
            type(CTRL + text)
    }

    private static TYRO_COMPONENT_NOT_FOUND_BY_FACTORY = 'TYRO_COMPONENT_NOT_FOUND_BY_FACTORY'

    private static <T extends Component> T createComponent(List<T> components, Class<T> clazz, String value, String selector) {
        switch (components.size()) {
            case 1:
                return components.first()
            case 2:
                throw new IllegalStateException("Find ${components.size()} component(s) ${clazz.simpleName} with ${selector} '${value}'.")
            default:
                // Return none existing component to be able to test availability
                provider.find(Config.componentTypes.find { clazz.isAssignableFrom(it) }, expression(TYRO_COMPONENT_NOT_FOUND_BY_FACTORY.toString()))
        }
    }
}