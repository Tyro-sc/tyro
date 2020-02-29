package sc.tyro.core.hamcrest

import org.hamcrest.Matcher
import sc.tyro.core.component.Component
import sc.tyro.core.component.Group
import sc.tyro.core.component.Item
import sc.tyro.core.component.datagrid.Cell
import sc.tyro.core.component.datagrid.Column
import sc.tyro.core.component.datagrid.Row
import sc.tyro.core.hamcrest.matcher.property.*
import sc.tyro.core.hamcrest.matcher.state.*
import sc.tyro.core.support.property.*
import sc.tyro.core.support.state.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
public class Matchers {
    static <T> Matcher<T> has(Matcher<T> matcher) { new Has(matcher) }

    // States
    static Matcher<Component> enabled() { new EnabledMatcher() }

    static Matcher<Component> disabled() { new DisabledMatcher() }

    static Matcher<Component> available() { new AvailableMatcher() }

    static Matcher<Component> missing() { new MissingMatcher() }

    static Matcher<Component> visible() { new VisibleMatcher() }

    static Matcher<Component> hidden() { new HiddenMatcher() }

    static Matcher<EmptySupport> empty() { new EmptyMatcher() }

    static Matcher<EmptySupport> filled() { new FilledMatcher() }

    static Matcher<FocusSupport> focused() { new FocusedMatcher() }

    static Matcher<ReadOnlySupport> readOnly() { new ReadOnlyMatcher() }

    static Matcher<RequiredSupport> required() { new RequiredMatcher() }

    static Matcher<RequiredSupport> optional() { new OptionalMatcher() }

    static Matcher<CheckSupport> checked() { new CheckedMatcher() }

    static Matcher<CheckSupport> unchecked() { new UnCheckedMatcher() }

    static Matcher<SelectSupport> selected() { new SelectedMatcher() }

    static Matcher<SelectSupport> unselected() { new UnSelectedMatcher() }

    static Matcher<RangeSupport> inRange() { new InRangeMatcher() }

    static Matcher<RangeSupport> outOfRange() { new OutOfRangeMatcher() }

    static Matcher<ValiditySupport> valid() { new ValidMatcher() }

    static Matcher<ValiditySupport> invalid() { new InvalidMatcher() }

    static Matcher<Component> contain(Component... components) {
        new ContainMatcher(components)
    }

    static Matcher<CollapseSupport> expanded() { new ExpandedMatcher() }

    static Matcher<CollapseSupport> collapsed() { new CollapsedMatcher() }

    // Properties
    static Matcher<LabelSupport> label(String label) { new LabelMatcher(label) }

    static Matcher<InputSupport> placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }

    static Matcher<MaximumSupport> maximum(Object maximum) { new MaximumMatcher(maximum) }

    static Matcher<MinimumSupport> minimum(Object minimum) { new MinimumMatcher(minimum) }

    static Matcher<LengthSupport> length(Object length) { new LengthMatcher(length) }

    static Matcher<StepSupport> step(Object minimum) { new StepMatcher(minimum) }

    static Matcher<TextSupport> text(String text) { new TextMatcher(text) }

    static Matcher<ValueSupport> value(Object value) { new ValueMatcher(value) }

    static Matcher<ReferenceSupport> reference(String source) { new ReferenceMatcher(source) }

    static Matcher<TitleSupport> title(String title) { new TitleMatcher(title) }

    static Matcher<ItemSupport> items(String... items) { new ItemMatcher(items) }

    static Matcher<ItemSupport> items(Item... items) { new ItemMatcher(items) }

    static Matcher<SelectedItemSupport> selectedItem(String value) { new SelectedItemMatcher(value) }

    static Matcher<SelectedItemSupport> selectedItem(Item value) { new SelectedItemMatcher(value) }

    static Matcher<SelectedItemsSupport> selectedItems(String... values) { new SelectedItemsMatcher(values) }

    static Matcher<SelectedItemsSupport> selectedItems(Item... values) { new SelectedItemsMatcher(values) }

    static Matcher<GroupSupport> groups(String... values) { new GroupMatcher(values) }

    static Matcher<GroupSupport> groups(Group... values) { new GroupMatcher(values) }

    static Matcher<ColumnSupport> columns(String... values) { new ColumnMatcher(values) }

    static Matcher<ColumnSupport> columns(Column... values) { new ColumnMatcher(values) }

    static Matcher<RowSupport> rows(String... values) { new RowMatcher(values) }

    static Matcher<RowSupport> rows(Row... values) { new RowMatcher(values) }

    static Matcher<CellSupport> cells(String... values) { new CellMatcher(values) }

    static Matcher<CellSupport> cells(Cell... values) { new CellMatcher(values) }
}