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

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Tyro.*
import static sc.tyro.core.input.Key.TAB
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

/**
 * @author David Avenante
 * @since 1.0.0
 */
@ExtendWith(TyroWebTestExtension)
@DisplayName("Input Validity Tests")
class InputValidityTest {
    @BeforeAll
    static void before() {
        visit BASE_URL + 'input-validity.html'
    }

    @Test
    @DisplayName("Should verify fields validity")
    void fieldsValidity() {
        field('Password').should {
            be invalid
            have errorMessage('Please fill out this field.')
        }

        field('Select').should {
            be valid
            have errorMessage('')
        }

        set field('Select') to 20

        field('Select').should {
            be invalid
            have errorMessage('Value must be less than or equal to 10.')
        }

        // Change validation message onBlur
        type TAB

        field('Select').should {
            be invalid
            have errorMessage('Invalid Tips')
        }
    }

    @Test
    @DisplayName("Should verify radio validity")
    void radioValidity() {
        radio('Male').should {
            be invalid
            have errorMessage('Please select one of these options.')
        }

        radio('Female').should {
            be invalid
            have errorMessage('Please select one of these options.')
        }

        check radio('Male')

        radio('Male').should {
            be valid
            have errorMessage('')
        }

        radio('Female').should {
            be valid
            have errorMessage('')
        }
    }

    @Test
    @DisplayName("Should verify checkbox validity")
    void checkboxValidity() {
        checkbox('Check me out').should {
            be invalid
            have errorMessage('Please check this box if you want to proceed.')
        }

        check checkbox('Check me out')

        checkbox('Check me out').should {
            be valid
            have errorMessage('')
        }
    }

    @Test
    @DisplayName("Should verify Dropdown validity")
    void dropDownValidity() {
        dropdown('Cities').should {
            be invalid
            have errorMessage('Please select an item in the list.')
        }

        dropdown('Cities').select('Montpellier')

        dropdown('Cities').should {
            be valid
            have errorMessage('')
        }
    }

    @Test
    @DisplayName("Should verify ListBox validity")
    void listBoxValidity() {
        listBox('Planets').should {
            be invalid
            have errorMessage('Please select an item in the list.')
        }

        listBox('Planets').select('Venus')

        listBox('Planets').should {
            be valid
            have errorMessage('')
        }
    }
}
