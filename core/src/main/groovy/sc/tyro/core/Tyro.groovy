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

import java.util.concurrent.TimeoutException

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

    static Button button(String text) { provider.find(By.text(text), Button) }

    static Radio radio(String label) { provider.find(By.label(label), Radio) }

    static CheckBox checkbox(String label) { provider.find(By.label(label), CheckBox) }

    static Dropdown dropdown(String label) { provider.find(By.label(label), Dropdown) }

    static ListBox listBox(String label) { provider.find(By.label(label), ListBox) }

    static Group group(String value) { provider.find(By.value(value), Group) }

    static Item item(String value) { provider.find(By.value(value), Item) }

    static Heading heading(String text) { provider.find(By.text(text), Heading) }

    static Panel panel(String title) { provider.find(By.title(title), Panel) }

    static Link link(String text) { provider.find(By.text(text), Link)  }

    static PasswordField passwordField(String label) { provider.find(By.label(label), PasswordField) }

    static TextField textField(String label) { provider.find(By.label(label), TextField) }

    static SearchField searchField(String label) { provider.find(By.label(label), SearchField) }

    static EmailField emailField(String label) { provider.find(By.label(label), EmailField) }

    static URLField urlField(String label) { provider.find(By.label(label), URLField) }

    static NumberField numberField(String label) { provider.find(By.label(label), NumberField) }

    static RangeField rangeField(String label) { provider.find(By.label(label), RangeField) }

    static DateField dateField(String label) { provider.find(By.label(label), DateField) }

    static ColorField colorField(String label) { provider.find(By.label(label), ColorField) }

    static DateTimeField dateTimeField(String label) { provider.find(By.label(label), DateTimeField) }

    static MonthField monthField(String label) { provider.find(By.label(label), MonthField) }

    static PhoneField phoneField(String label) { provider.find(By.label(label), PhoneField) }

    static TimeField timeField(String label) { provider.find(By.label(label), TimeField) }

    static WeekField weekField(String label) { provider.find(By.label(label), WeekField) }

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
}
