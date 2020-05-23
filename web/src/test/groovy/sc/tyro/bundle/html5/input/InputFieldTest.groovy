package sc.tyro.bundle.html5.input

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.ComponentException
import sc.tyro.core.component.field.*
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Input Field Tests")
class InputFieldTest {
    @BeforeAll
    static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    void input_should_have_expected_behaviours() {
        browser().refresh()

        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email') as InputTypeEmail
        assert email.empty()
        assert !email.required()
        assert !email.readOnly()
        assert email.valid()
        assert email.value() == ''
        assert email.focused()

        TextField text = $('#text_field') as InputTypeText
        assert text.placeholder() == 'Placeholder'
        assert !text.focused()

        text = $('#read_only_and_filled') as InputTypeText
        assert !text.empty()
        assert text.readOnly()
        assert text.value() == 'Filled'

        try {
            text.value('New Value')
            fail()
        } catch (ComponentException e) {
            assert e.message == 'InputTypeText InputTypeText:read_only_and_filled is disabled and cannot be filled'
        }

        PasswordField password = $('#password') as InputTypePassword
        assert password.required()
        // Invalid cause required
        assert !password.valid()

        password.value('My Password')
        password.should { have value('My Password') }

        password.clear()
        password.should { have value('') }
    }

    @Test
    void color_field_should_have_expected_behaviours() {
        InputTypeColor in ColorField
        InputTypeColor in Input

        InputTypeColor colorField = $('#color_field') as InputTypeColor
        assert colorField.label() == 'Color'

        // Fail on CI
        assert colorField.value() == '#000000'
        colorField.value('#ff0000')
        colorField.should { have value('#ff0000') }
        assert colorField.valid()
    }

    @Test
    void date_field_should_have_expected_behaviours() {
        InputTypeColor in DateField
        InputTypeColor in Input

        InputTypeDate date = $('#date_field') as InputTypeDate
        assert date.value() == ''
        assert date.step() == 0
        assert date.inRange()
        assert date.minimum() == '2011-08-13'
        assert date.maximum() == '2012-06-25'

        date.value('2010-06-25')
        date.should { have value('2010-06-25') }
    }

    @Test
    void dateTime_field_should_have_expected_behaviours() {
        InputTypeDateTime in DateTimeField
        InputTypeDateTime in Input

        DateTimeField dateTime = $('#datetime_field') as InputTypeDateTime

        assert dateTime.value() == ''
        dateTime.value('2010-06-25')
        dateTime.should { have value('2010-06-25') }
    }

    @Test
    void email_field_should_have_expected_behaviours() {
        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email_field') as InputTypeEmail
        assert email.label() == 'Email'
    }

    @Test
    void month_field_should_have_expected_behaviours() {
        InputTypeMonth in MonthField
        InputTypeMonth in Input

        InputTypeMonth month = $('#month_field') as InputTypeMonth
        assert month.label() == 'Month'
    }

    @Test
    void number_field_should_have_expected_behaviours() {
        InputTypeNumber in NumberField
        InputTypeNumber in Input

        InputTypeNumber number = $('#number_field') as InputTypeNumber
        assert number.label() == 'Number'

        assert number.maximum() == 64
        assert number.minimum() == 0
        assert number.step() == 8
        assert number.value() == 2
        assert number.inRange()

        number.value('150')
        number.should {
            have value(150)
            be outOfRange
        }
    }

    @Test
    void password_field_should_have_expected_behaviours() {
        InputTypePassword in PasswordField
        InputTypePassword in Input

        InputTypePassword password = $('#password_field') as InputTypePassword
        assert password.label() == 'Password'
        assert password.length() == 20
    }

    @Test
    void phone_field_should_have_expected_behaviours() {
        InputTypeTel in PhoneField
        InputTypeTel in Input

        InputTypeTel phone = $('#phone_field') as InputTypeTel
        assert phone.pattern == '^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$'
    }

    @Test
    void range_field_should_have_expected_behaviours() {
        InputTypeRange in RangeField
        InputTypeRange in Input

        InputTypeRange range = $('#range_field') as InputTypeRange
        assert range.maximum() == 50
        assert range.minimum() == 0
        assert range.step() == 5
        assert range.inRange()

        assert range.value() == 10
        range.value(40)
        range.should { have value(40) }

        // Cause step 5
        range.value(42)
        range.should { have value(40) }
    }

    @Test
    void search_field_should_have_expected_behaviours() {
        InputTypeSearch in SearchField
        InputTypeSearch in Input

        InputTypeSearch searchField = $('#search_field') as InputTypeSearch
        assert searchField.label() == 'Search'
        assert searchField.length() == 200

        searchField.value() == ''
        searchField.value('my search')
        searchField.should { have value('my search') }
    }

    @Test
    void text_field_should_have_expected_behaviours() {
        InputTypeText in TextField
        InputTypeText in Input

        TextField text = $('#text_field') as InputTypeText
        assert text.label() == 'Text'
        assert text.length() == 20
    }

    @Test
    void area_field_should_have_expected_behaviours() {
        TextArea in TextField
        TextArea in Input

        TextArea text = $('#text_area_field') as TextArea
        assert text.label() == 'TextArea'
        assert text.length() == 140
    }

    @Test
    void time_field_should_have_expected_behaviours() {
        InputTypeTime in TimeField
        InputTypeTime in Input

        InputTypeTime time = $('#time_field') as InputTypeTime
        assert time.label() == 'Time'

        assert time.value() == ''
        time.value('16:45')
        assert time.value() == '16:45'
    }

    @Test
    void url_field_should_have_expected_behaviours() {
        InputTypeURL in URLField
        InputTypeURL in Input

        InputTypeURL url = $('#url_field') as InputTypeURL
        assert url.label() == 'URL'

        assert url.value() == ''
        url.value('http://mysite')
        url.should {
            have value('http://mysite')
            have length(150)
        }
    }

    @Test
    void week_field_should_have_expected_behaviours() {
        InputTypeWeek in WeekField
        InputTypeWeek in Input

        WeekField week = $('#week_field') as InputTypeWeek
        assert week.label() == 'Week'

        assert week.value() == ''
        week.value('2016-W32')
        week.should { have value('2016-W32') }
    }
}