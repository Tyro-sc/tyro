package sc.tyro.core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.*
import sc.tyro.core.component.field.*

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.*
import static sc.tyro.core.Tyro.*

@DisplayName("Tyro base class Tests")
class TyroTest {
    private Provider provider

    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        Config.provider = provider
    }

    @Test
    @DisplayName("Should expose a convenient's method to find a component by expression")
    void findOneWith$() {
        $('expression')

        verify(provider, times(1)).find(By.expression('expression'), Component)
    }

    @Test
    @DisplayName("Should expose a convenient's method to find a list of components by expression")
    void findManyWith$$() {
        $$('expression', Button)

        verify(provider, times(1)).findAll(By.expression('expression'), Button)
    }

    @Test
    @DisplayName("Should find Button by text")
    void findButtonByText() {
        Button _button = mock(Button)
        when(_button.text()).thenReturn('Ok')

        when(provider.find(By.text('Ok'), Button)).thenReturn(_button)

        Button button = button('Ok')

        assertThat(button.text(), is('Ok'))
    }

    @Test
    @DisplayName("Should find Radio by label")
    void findRadioByLabel() {
        Radio _radio = mock(Radio)
        when(_radio.label()).thenReturn('label')

        when(provider.find(By.label('label'), Radio)).thenReturn(_radio)

        Radio radio = radio('label')

        assertThat(radio.label(), is('label'))
    }

    @Test
    @DisplayName("Should find CheckBox by label")
    void findCheckBoxByLabel() {
        CheckBox _checkBox = mock(CheckBox)
        when(_checkBox.label()).thenReturn('label')

        when(provider.find(By.label('label'), CheckBox)).thenReturn(_checkBox)

        CheckBox checkBox = checkbox('label')

        assertThat(checkBox.label(), is('label'))
    }

    @Test
    @DisplayName("Should find Dropdown by label")
    void findDropdownByLabel() {
        Dropdown _dropdown = mock(Dropdown)
        when(_dropdown.label()).thenReturn('label')

        when(provider.find(By.label('label'), Dropdown)).thenReturn(_dropdown)

        Dropdown dropdown = dropdown('label')

        assertThat(dropdown.label(), is('label'))
    }

    @Test
    @DisplayName("Should find ListBox by label")
    void findListBoxByLabel() {
        ListBox _listBox = mock(ListBox)
        when(_listBox.label()).thenReturn('label')

        when(provider.find(By.label('label'), ListBox)).thenReturn(_listBox)

        ListBox listBox = listBox('label')

        assertThat(listBox.label(), is('label'))
    }

    @Test
    @DisplayName("Should find Group by value")
    void findGroupByValue() {
        Group _group = mock(Group)
        when(_group.value()).thenReturn('value')

        when(provider.find(By.value('value'), Group)).thenReturn(_group)

        Group group = group('value')

        assertThat(group.value(), is('value'))
    }

    @Test
    @DisplayName("Should find Item by value")
    void findItemByValue() {
        Item _item = mock(Item)
        when(_item.value()).thenReturn('value')

        when(provider.find(By.value('value'), Item)).thenReturn(_item)

        Item item = item('value')

        assertThat(item.value(), is('value'))
    }

    @Test
    @DisplayName("Should find Heading by text")
    void findHeadingByText() {
        Heading _heading = mock(Heading)
        when(_heading.text()).thenReturn('text')

        when(provider.find(By.text('text'), Heading)).thenReturn(_heading)

        Heading heading = heading('text')

        assertThat(heading.text(), is('text'))
    }

    @Test
    @DisplayName("Should find Panel by title")
    void findPanelByTitle() {
        Panel _panel = mock(Panel)
        when(_panel.title()).thenReturn('title')

        when(provider.find(By.title('title'), Panel)).thenReturn(_panel)

        Panel panel = panel('title')

        assertThat(panel.title(), is('title'))
    }

    @Test
    @DisplayName("Should find Link by text")
    void findLinkByText() {
        Link _link = mock(Link)
        when(_link.text()).thenReturn('text')

        when(provider.find(By.text('text'), Link)).thenReturn(_link)

        Link link = link('text')

        assertThat(link.text(), is('text'))
    }

    @Test
    @DisplayName("Should find PasswordField by label")
    void findPasswordFieldByLabel() {
        PasswordField _passwordField = mock(PasswordField)
        when(_passwordField.label()).thenReturn("label")

        when(provider.find(By.label('label'), PasswordField)).thenReturn(_passwordField)

        PasswordField passwordField = passwordField('label')

        assertThat(passwordField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find TextField by label")
    void findTextFieldByLabel() {
        TextField _textField = mock(TextField)
        when(_textField.label()).thenReturn("label")

        when(provider.find(By.label('label'), TextField)).thenReturn(_textField)

        TextField textField = textField('label')

        assertThat(textField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find SearchField by label")
    void findSearchFieldByLabel() {
        SearchField _searchField = mock(SearchField)
        when(_searchField.label()).thenReturn("label")

        when(provider.find(By.label('label'), SearchField)).thenReturn(_searchField)

        SearchField searchField = searchField('label')

        assertThat(searchField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find EmailField by label")
    void findEmailFieldByLabel() {
        EmailField _emailField = mock(EmailField)
        when(_emailField.label()).thenReturn("label")

        when(provider.find(By.label('label'), EmailField)).thenReturn(_emailField)

        EmailField emailField = emailField('label')

        assertThat(emailField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find URLField by label")
    void findURLFieldByLabel() {
        URLField _urlField = mock(URLField)
        when(_urlField.label()).thenReturn("label")

        when(provider.find(By.label('label'), URLField)).thenReturn(_urlField)

        URLField urlField = urlField('label')

        assertThat(urlField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find NumberField by label")
    void findNumberFieldByLabel() {
        NumberField _numberField = mock(NumberField)
        when(_numberField.label()).thenReturn("label")

        when(provider.find(By.label('label'), NumberField)).thenReturn(_numberField)

        NumberField numberField = numberField('label')

        assertThat(numberField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find RangeField by label")
    void findRangeFieldByLabel() {
        RangeField _rangeField = mock(RangeField)
        when(_rangeField.label()).thenReturn("label")

        when(provider.find(By.label('label'), RangeField)).thenReturn(_rangeField)

        RangeField rangeField = rangeField('label')

        assertThat(rangeField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find DateField by label")
    void findDateFieldByLabel() {
        DateField _dateField = mock(DateField)
        when(_dateField.label()).thenReturn("label")

        when(provider.find(By.label('label'), DateField)).thenReturn(_dateField)

        DateField dateField = dateField('label')

        assertThat(dateField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find ColorField by label")
    void findColorFieldByLabel() {
        ColorField _colorField = mock(ColorField)
        when(_colorField.label()).thenReturn("label")

        when(provider.find(By.label('label'), ColorField)).thenReturn(_colorField)

        ColorField colorField = colorField('label')

        assertThat(colorField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find DateTimeField by label")
    void findDateTimeFieldByLabel() {
        DateTimeField _dateTimeField = mock(DateTimeField)
        when(_dateTimeField.label()).thenReturn("label")

        when(provider.find(By.label('label'), DateTimeField)).thenReturn(_dateTimeField)

        DateTimeField dateTimeField = dateTimeField('label')

        assertThat(dateTimeField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find MonthField by label")
    void findMonthFieldByLabel() {
        MonthField _monthField = mock(MonthField)
        when(_monthField.label()).thenReturn("label")

        when(provider.find(By.label('label'), MonthField)).thenReturn(_monthField)

        MonthField monthField = monthField('label')

        assertThat(monthField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find PhoneField by label")
    void findPhoneFieldByLabel() {
        PhoneField _phoneField = mock(PhoneField)
        when(_phoneField.label()).thenReturn("label")

        when(provider.find(By.label('label'), PhoneField)).thenReturn(_phoneField)

        PhoneField phoneField = phoneField('label')

        assertThat(phoneField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find TimeField by label")
    void findTimeFieldByLabel() {
        TimeField _timeField = mock(TimeField)
        when(_timeField.label()).thenReturn("label")

        when(provider.find(By.label('label'), TimeField)).thenReturn(_timeField)

        TimeField timeField = timeField('label')

        assertThat(timeField.label(), is('label'))
    }

    @Test
    @DisplayName("Should find WeekField by label")
    void findWeekFieldByLabel() {
        WeekField _weekField = mock(WeekField)
        when(_weekField.label()).thenReturn("label")

        when(provider.find(By.label('label'), WeekField)).thenReturn(_weekField)

        WeekField weekField = weekField('label')

        assertThat(weekField.label(), is('label'))
    }
}
