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
import sc.tyro.core.By
import sc.tyro.web.TyroWebTestExtension

import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.visit
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
}
