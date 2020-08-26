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
package sc.tyro.bundle.html5

import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.By
import sc.tyro.core.component.field.EmailField
import sc.tyro.core.component.field.Field
import sc.tyro.core.component.field.PasswordField
import sc.tyro.web.TyroWebTestExtension

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static org.junit.jupiter.api.Assertions.assertThrows
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.*
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Factories Tests")
class FactoryTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'factory.html'
    }

    @Test
    @DisplayName("Should find unique component by expression")
    void find() {
        Button button = provider.find(Button, By.expression('button'))

        assert button.visible()
    }

    @Test
    @DisplayName("Should find all components by expression")
    void findAllByExpression() {
        List<Button> buttons = provider.findAll(Button, By.expression('.btn-primary'))

        assert buttons.size() == 4
    }

    @Test
    @DisplayName("Should find all components by type")
    void findAll() {
        // Abstract class
        List<sc.tyro.core.component.Button> componentFromAbstract = provider.findAll(sc.tyro.core.component.Button)

        assert componentFromAbstract.size() == 5

        // Concrete class
        List<Button> componentFromConcrete = provider.findAll(Button)

        assert componentFromConcrete.size() == 5
    }

    @Test
    @DisplayName("Should find fields components with expected type")
    void fieldWithExpectedType() {
        List<Field> fields = findAll(Field)

        assert fields.size() == 2

        Field field_1 = field('Email')

        assert field_1.label() == 'Email'

        field_1 = field('Password')

        assert field_1.label() == 'Password'

        EmailField email = field("Email", EmailField)

        assert email.label() == 'Email'

        IllegalStateException error = assertThrows(IllegalStateException, { field("Email", PasswordField) })
        assertThat(error.message, is('Find 0 component(s) PasswordField with label \'Email\'.'))

        ClassCastException classCastError = assertThrows(GroovyCastException, { PasswordField password = field("Email", EmailField) })
        assertThat(classCastError.message, startsWith("Cannot cast object 'InputTypeEmail"))
        assertThat(classCastError.message, endsWith("with class 'sc.tyro.bundle.html5.input.InputTypeEmail' to class 'sc.tyro.core.component.field.PasswordField'"))
    }
}
