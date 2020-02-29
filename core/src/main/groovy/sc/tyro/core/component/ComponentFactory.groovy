package sc.tyro.core.component

import sc.tyro.core.component.field.*

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
public class ComponentFactory {
    static Button button(String text) { collectAll(Button).find { it.text() == text } }

    static Radio radio(String label) { collectAll(Radio).find { it.label() == label } }

    static CheckBox checkbox(String label) { collectAll(CheckBox).find { it.label() == label } }

    static Dropdown dropdown(String label) { collectAll(Dropdown).find { it.label() == label } }

    static ListBox listBox(String label) { collectAll(ListBox).find { it.label() == label } }

    static Group group(String value) { collectAll(Group).find { it.value() == value } }

    static Item item(String value) { collectAll(Item).find { it.value() == value } }

    static Heading heading(String text) { collectAll(Heading).find { it.text() == text } }

    static Panel panel(String title) { collectAll(Panel).find { it.title() == title } }

    static Link link(String text) { collectAll(Link).find { it.text() == text } }

    static PasswordField passwordField(String value) { field(value, PasswordField) }

    static TextField textField(String value) { field(value, TextField) }

    static SearchField searchField(String value) { field(value, SearchField) }

    static EmailField emailField(String value) { field(value, EmailField) }

    static URLField urlField(String value) { field(value, URLField) }

    static NumberField numberField(String value) { field(value, NumberField) }

    static RangeField rangeField(String value) { field(value, RangeField) }

    static DateField dateField(String value) { field(value, DateField) }

    static ColorField colorField(String value) { field(value, ColorField) }

    static DateTimeField dateTimeField(String value) { field(value, DateTimeField) }

    static MonthField monthField(String value) { field(value, MonthField) }

    static PhoneField phoneField(String value) { field(value, PhoneField) }

    static TimeField timeField(String value) { field(value, TimeField) }

    static WeekField weekField(String value) { field(value, WeekField) }

    private static <T extends Field> T field(String value, Class<T> clazz) {
        return null
//        T field = collectAll(clazz).find { it.label() == value || it.placeholder() == value }
//        if (field)
//            return field
//        throw new ComponentException("Unable to find $clazz with label or placeholder equals to '$value'")
    }

//    private static <T extends Component> List<T> collectAll(Class<T> clazz) {
//        Identifiers.findSelectorsFor(clazz).collectMany {
//            org.testattoo.core.Testattoo.$$(it.value, it.key)
//        }
//    }
}