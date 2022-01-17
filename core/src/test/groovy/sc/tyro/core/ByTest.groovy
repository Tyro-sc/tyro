/*
 * Copyright © 2021 Ovea (d.avenante@gmail.com)
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
package sc.tyro.core

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("By Tests")
class ByTest {
    @Test
    @DisplayName("Should instantiate ById")
    void byId() {
        By.ById by = By.id('id')

        assertThat(by.id, is('id'))
        assertThat(by.hashCode(), is('id'.hashCode()))
        assertThat(by.toString(), is('id: id'))
    }

    @Test
    @DisplayName("Should have equality on id for ById")
    void byIdEquality() {
        By.ById by_1 = By.id('id_1')
        By.ById by_2 = By.id('id_2')
        By.ById by_3 = By.id('id_1')

        assertThat(by_1, is(not(equalTo(by_2))))
        assertThat(by_1, is(equalTo(by_3)))
    }

    @Test
    @DisplayName("Should instantiate ByExpression")
    void byExpression() {
        By.ByExpression by = By.expression('expression')

        assertThat(by.expression, is('expression'))
        assertThat(by.hashCode(), is('expression'.hashCode()))
        assertThat(by.toString(), is('expression'))
    }

    @Test
    @DisplayName("Should have equality on expression for ByExpression")
    void byExpressionEquality() {
        By.ByExpression by_1 = By.expression('ex_1')
        By.ByExpression by_2 = By.expression('ex_2')
        By.ByExpression by_3 = By.expression('ex_1')

        assertThat(by_1, is(not(equalTo(by_2))))
        assertThat(by_1, is(equalTo(by_3)))
    }
}