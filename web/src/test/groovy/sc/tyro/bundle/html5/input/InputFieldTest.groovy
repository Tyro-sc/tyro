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
package sc.tyro.bundle.html5.input

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.ComponentException
import sc.tyro.core.component.field.*
import sc.tyro.web.TyroWebTestExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Input Field Tests")
class InputFieldTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'components.html'
    }

    @Test
    @DisplayName("Should have expected behaviours for Field")
    void field() {
        browser().refresh()

        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email') as InputTypeEmail
        assert email.empty()
        assert !email.required()
        assert !email.readOnly()
        assert email.valid()
        assert email.errorMessage() == ''
        assert email.value() == ''
        assert email.focused()

        TextField text = $('#text_field') as InputTypeText
        assert text.placeholder() == 'Placeholder'
        assert !text.focused()

        text = $('#read_only_and_filled') as InputTypeText
        assert !text.empty()
        assert text.readOnly()
        assert text.value() == 'Filled'

        Exception ex = assertThrows(ComponentException, { text.value('New Value') })
        assert ex.message == 'InputTypeText InputTypeText:read_only_and_filled is disabled and cannot be filled'

        PasswordField password = $('#password') as InputTypePassword
        assert password.required()
        // Invalid cause required
        assert !password.valid()
        assert password.errorMessage() == 'Please fill out this field.'

        password.value('My Password')
        password.should { have value('My Password') }

        password.clear()
        password.should { have value('') }
    }

    @Test
    @DisplayName("Should have expected behaviours for ColorField")
    void colorField() {
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
    @DisplayName("Should have expected behaviours for DateField")
    void dateField() {
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
    @DisplayName("Should have expected behaviours for DateTime")
    void dateTimeField() {
        InputTypeDateTimeLocal in DateTimeField
        InputTypeDateTimeLocal in Input

        DateTimeField dateTime = $('#datetime_field') as InputTypeDateTimeLocal

        assert dateTime.value() == ''
        dateTime.value('2010-06-25T10:15')
        dateTime.should { have value('2010-06-25T10:15') }
    }

    @Test
    @DisplayName("Should have expected behaviours for EmailField")
    void emailField() {
        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email_field') as InputTypeEmail
        assert email.label() == 'Email'
    }

    @Test
    @DisplayName("Should have expected behaviours for MonthField")
    void monthField() {
        InputTypeMonth in MonthField
        InputTypeMonth in Input

        InputTypeMonth month = $('#month_field') as InputTypeMonth
        assert month.label() == 'Month'
    }

    @Test
    @DisplayName("Should have expected behaviours for NumberField")
    void numberField() {
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
    @DisplayName("Should have expected behaviours for PasswordField")
    void passwordField() {
        InputTypePassword in PasswordField
        InputTypePassword in Input

        InputTypePassword password = $('#password_field') as InputTypePassword
        assert password.label() == 'Password'
        assert password.length() == 20
    }

    @Test
    @DisplayName("Should have expected behaviours for PhoneField")
    void phoneField() {
        InputTypeTel in PhoneField
        InputTypeTel in Input

        InputTypeTel phone = $('#phone_field') as InputTypeTel
        assert phone.pattern == '^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$'
    }

    @Test
    @DisplayName("Should have expected behaviours for RangeField")
    void rangeField() {
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
    @DisplayName("Should have expected behaviours for SearchField")
    void searchField() {
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
    @DisplayName("Should have expected behaviours for TextField")
    void textField() {
        InputTypeText in TextField
        InputTypeText in Input

        TextField text = $('#text_field') as InputTypeText
        assert text.label() == 'Text'
        assert text.length() == 20
    }

    @Test
    @DisplayName("Should have expected behaviours for TextAreaField")
    void textAreaField() {
        TextArea in TextField
        TextArea in Input

        TextArea text = $('#text_area_field') as TextArea
        assert text.label() == 'TextArea'
        assert text.length() == 140
    }

    @Test
    @DisplayName("Should have expected behaviours for TimeField")
    void timeField() {
        InputTypeTime in TimeField
        InputTypeTime in Input

        InputTypeTime time = $('#time_field') as InputTypeTime
        assert time.label() == 'Time'

        assert time.value() == ''
        time.value('16:45')
        assert time.value() == '16:45'
    }

    @Test
    @DisplayName("Should have expected behaviours for UrlField")
    void urlField() {
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
    @DisplayName("Should have expected behaviours for WeekField")
    void weekField() {
        InputTypeWeek in WeekField
        InputTypeWeek in Input

        WeekField week = $('#week_field') as InputTypeWeek
        assert week.label() == 'Week'

        assert week.value() == ''
        week.value('2016-W32')
        week.should { have value('2016-W32') }
    }
}