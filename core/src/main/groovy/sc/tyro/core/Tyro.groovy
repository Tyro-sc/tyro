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

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Tyro {
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

    static void check(Checkable c) {
        if (!c.enabled())
            throw new ComponentException("${c.class.simpleName} ${c} is disabled and cannot be checked")
        if (c.checked())
            throw new ComponentException("${c.class.simpleName} ${c} is already checked and cannot be checked")
        c.click()
    }

    static void uncheck(UnCheckable c) {
        if (!c.enabled())
            throw new ComponentException("${c.class.simpleName} ${c} is disabled and cannot be unchecked")
        if (!c.checked())
            throw new ComponentException("${c.class.simpleName} ${c} is already unchecked and cannot be unchecked")
        c.click()
    }

    static void clear(Clearable c) { c.clear() }

    static void reset(Resettable c) { c.reset() }

    static void submit(Submissible c) { c.submit() }

    static <T extends Component> T on(Component c) { c as T }

    static final FillAction fill(InputSupport c) { new FillAction(c) }

    static final FillAction set(InputSupport c) { new FillAction(c) }

//    static class Components<T extends Component> {
//        private final Provider provider
//        private final MetaDataProvider meta
//        private final Class<T> type
//        private List<T> components
//
//        Components(Class<T> type, Provider provider, MetaDataProvider meta) {
//            this.meta = meta
//            this.type = type
//            this.provider = provider
//        }
//
//        List<T> list() {
//            if (components == null) {
//                components = meta.metaInfos().collect {
//                    new Component(provider, new CachedMetaData(idProvider: Config.idProvider.get("#${it.id}", false))).asType(type)
//                } as List<T>
//            }
//            return Collections.unmodifiableList(components)
//        }
//    }

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

    static Button button(String text) { ComponentFactory.button(text) }

    static Radio radio(String label) { ComponentFactory.radio(label) }

    static CheckBox checkbox(String label) { ComponentFactory.checkbox(label) }

    static Dropdown dropdown(String label) { ComponentFactory.dropdown(label) }

    static ListBox listBox(String label) { ComponentFactory.listBox(label) }

    static Group group(String value) { ComponentFactory.group(value) }

    static Item item(String value) { ComponentFactory.item(value) }

    static Heading heading(String text) { ComponentFactory.heading(text) }

    static Panel panel(String title) { ComponentFactory.panel(title) }

    static Link link(String text) { ComponentFactory.link(text) }

    static PasswordField passwordField(String value) { ComponentFactory.passwordField(value) }

    static TextField textField(String value) { ComponentFactory.textField(value) }

    static SearchField searchField(String value) { ComponentFactory.searchField(value) }

    static EmailField emailField(String value) { ComponentFactory.emailField(value) }

    static URLField urlField(String value) { ComponentFactory.urlField(value) }

    static NumberField numberField(String value) { ComponentFactory.numberField(value) }

    static RangeField rangeField(String value) { ComponentFactory.rangeField(value) }

    static DateField dateField(String value) { ComponentFactory.dateField(value) }

    static ColorField colorField(String value) { ComponentFactory.colorField(value) }

    static DateTimeField dateTimeField(String value) { ComponentFactory.dateTimeField(value) }

    static MonthField monthField(String value) { ComponentFactory.monthField(value) }

    static PhoneField phoneField(String value) { ComponentFactory.phoneField(value) }

    static TimeField timeField(String value) { ComponentFactory.timeField(value) }

    static WeekField weekField(String value) { ComponentFactory.weekField(value) }

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
