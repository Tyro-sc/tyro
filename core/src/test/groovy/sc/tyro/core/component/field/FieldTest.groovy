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
package sc.tyro.core.component.field

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Component
import sc.tyro.core.support.property.InputSupport
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.property.LengthSupport
import sc.tyro.core.support.property.ValueSupport
import sc.tyro.core.support.state.FocusSupport
import sc.tyro.core.support.state.RangeSupport
import sc.tyro.core.support.state.ValiditySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Fields Components Tests")
class FieldTest {
    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Field in Component
        assert Field in LabelSupport
        assert Field in InputSupport
        assert Field in ValueSupport
        assert Field in ValiditySupport
        assert Field in FocusSupport

        assert ColorField in Field

        assert DateField in Field
        assert DateField in RangeSupport

        assert DateTimeField in Field

        assert EmailField in Field

        assert MonthField in Field

        assert NumberField in Field
        assert NumberField in RangeSupport

        assert PasswordField in Field
        assert PasswordField in LengthSupport

        assert TextField in Field
        assert TextField in LengthSupport

        assert PhoneField in Field

        assert RangeField in Field
        assert RangeField in RangeSupport

        assert SearchField in Field
        assert SearchField in LengthSupport

        assert TimeField in Field

        assert URLField in Field
        assert URLField in LengthSupport

        assert WeekField in Field
    }
}