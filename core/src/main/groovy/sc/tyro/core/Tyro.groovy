/**
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
import sc.tyro.core.component.field.*
import sc.tyro.core.hamcrest.matcher.property.*
import sc.tyro.core.hamcrest.matcher.state.*
import sc.tyro.core.input.DragBuilder
import sc.tyro.core.input.Key
import sc.tyro.core.input.Keyboard
import sc.tyro.core.input.Mouse
import sc.tyro.core.internal.Wait
import sc.tyro.core.support.*
import sc.tyro.core.support.property.InputSupport

import static sc.tyro.core.Config.provider
import static sc.tyro.core.input.Key.CTRL

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Tyro {
    static Component $(String expression) {
        provider.find(By.expression(expression), Component)
    }

    static <T extends Component> List<T> $$(String expression, Class<T> clazz = Component) {
        provider.findAll(By.expression(expression), clazz)
    }

    static <T extends Component> List<T> findBy(Class<T> clazz = Component) {
        provider.findBy(clazz)
    }

    static mouse = new Mouse()
    static keyboard = new Keyboard()
    static wait = new Wait()

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

    static StepMatcher step(object) { new StepMatcher(object) }

    static TextMatcher text(String text) { new TextMatcher(text) }

    static ValueMatcher value(Object value) { new ValueMatcher(value) }

    static ReferenceMatcher reference(String reference) { new ReferenceMatcher(reference) }

    static TitleMatcher title(String title) { new TitleMatcher(title) }

    /**
     * Actions
     */
    static void visit(String uri) { browser().open(uri) }

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

    static void select(Item... items) {
        items.each {
            if (!it.enabled())
                throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be selected")
            if (it.selected()) {
                throw new ComponentException("${it.class.simpleName} ${it} is already selected and cannot be selected")
            }
            CTRL.click it
        }
    }

    static void unselect(Item... items) {
        items.each {
            if (!it.enabled())
                throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be deselected")
            if (!it.selected()) {
                throw new ComponentException("${it.class.simpleName} ${it} is already unselected and cannot be deselected")
            }
            CTRL.click it
        }
    }

    static void clear(Clearable c) { c.clear() }

    static void reset(Resettable c) { c.reset() }

    static void submit(Submissible c) { c.submit() }

    static <T extends Component> T on(Component c) { c as T }

    static final FillAction fill(InputSupport c) { new FillAction(c) }

    static final FillAction set(InputSupport c) { new FillAction(c) }

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

    static Button button(String text) { findByText(Button, text) }

    static Radio radio(String label) { provider.findBy(Radio).find { it.label() == label } }

    static CheckBox checkbox(String label) { provider.findBy(CheckBox).find { it.label() == label } }

    static Dropdown dropdown(String label) { provider.findBy(Dropdown).find { it.label() == label } }

    static ListBox listBox(String label) { provider.findBy(ListBox).find { it.label() == label } }

    static Group group(String value) { provider.findBy(Group).find { it.value() == value } }

    static Item item(String value) { provider.findBy(Item).find { it.value() == value } }

    static Heading heading(String text) { findByText(Heading, text) }

    static Panel panel(String title) { provider.findBy(Panel).find { it.title() == title } }

    static Link link(String text) { findByText(Link, text) }

    static PasswordField passwordField(String label) { provider.findBy(PasswordField).find { it.label() == label } }

    static TextField textField(String label) { provider.findBy(TextField).find { it.label() == label } }

    static SearchField searchField(String label) { provider.findBy(SearchField).find { it.label() == label } }

    static EmailField emailField(String label) { provider.findBy(EmailField).find { it.label() == label } }

    static URLField urlField(String label) { provider.findBy(URLField).find { it.label() == label } }

    static NumberField numberField(String label) { provider.findBy(NumberField).find { it.label() == label } }

    static RangeField rangeField(String label) { provider.findBy(RangeField).find { it.label() == label } }

    static DateField dateField(String label) { provider.findBy(DateField).find { it.label() == label } }

    static ColorField colorField(String label) { provider.findBy(ColorField).find { it.label() == label } }

    static DateTimeField dateTimeField(String label) { provider.findBy(DateTimeField).find { it.label() == label } }

    static MonthField monthField(String label) { provider.findBy(MonthField).find { it.label() == label } }

    static PhoneField phoneField(String label) { provider.findBy(PhoneField).find { it.label() == label } }

    static TimeField timeField(String label) { provider.findBy(TimeField).find { it.label() == label } }

    static WeekField weekField(String label) { provider.findBy(WeekField).find { it.label() == label } }

    static void waitUntil(Closure c, Matcher what = null) { wait.waitUntil(c, what) }

    private static class FillAction {
        private InputSupport input

        FillAction(InputSupport input) {
            this.input = input
        }

        void with(Object value) {
            input.value(value)
        }

        void to(Object value) {
            input.value(value)
        }
    }

    private static <T extends Component> T unique(Collection<T> components) {
        if(components.size() == 1 ) {
            return components.first()
        }
        throw new IllegalStateException("")

    }

    protected static <T extends Component> T findByLabel(T clazz, String label) {
        Collection<T> components = provider.findBy(clazz).findAll { it.label() == label }
        if(components.size() == 1 ) {
            return components.first()
        }
        throw new IllegalStateException("Find many components ${clazz.simpleName} with label '${label}'.")
    }

    protected static <T extends Component> T findByText(Class clazz , String text) {
        Collection<T> components = provider.findBy(clazz).findAll { it.text() == text }
        if(components.size() == 1 ) {
            return components.first()
        }
        throw new IllegalStateException("Find many components ${clazz.simpleName} with text '${text}'.")
    }
}
