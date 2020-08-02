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
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.Config
import sc.tyro.core.component.Button
import sc.tyro.web.TyroWebTestExtension

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.hasSize
import static sc.tyro.core.Tyro.findBy
import static sc.tyro.core.Tyro.visit
import static sc.tyro.web.TyroWebTestExtension.BASE_URL

@ExtendWith(TyroWebTestExtension)
@DisplayName("Components Factory Tests")
class ComponentsFactoryTest {
    @BeforeAll
    static void before() {
//        visit 'http://localhost:8080/factory.html'
        visit BASE_URL + 'components.html'
    }

    @Disabled
    @Test
    @DisplayName("Should find components by type")
    void findByType() {
        Config.provider.eval(null, "\$('#range_field').val(20)")

//        "it.val(" + value + ")")

//        List<Button> buttons = findBy(Button)
//
//        assertThat(buttons, hasSize(5))

    }

}
