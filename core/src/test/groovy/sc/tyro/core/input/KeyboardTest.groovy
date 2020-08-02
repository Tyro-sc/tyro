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
package sc.tyro.core.input

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.Provider

import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider
import static sc.tyro.core.input.Key.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Keyboard Interactions Tests")
class KeyboardTest {
    private Keyboard keyboard = new Keyboard()

    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        keyboard = new Keyboard()
    }

    @Test
    @DisplayName("Should Type Characters")
    void characters() {
        keyboard.type(['a', 'b', 'c', 'd', 'e', '1', '2'])

        verify(provider, times(1)).type(['a', 'b', 'c', 'd', 'e', '1', '2'])
    }

    @Test
    @DisplayName("Should Type Special Keys")
    void specialKeys() {
        keyboard.type([DIVIDE, MULTIPLY, SUBTRACT, ADD, EQUALS, RETURN, SPACE])

        verify(provider, times(1)).type([DIVIDE, MULTIPLY, SUBTRACT, ADD, EQUALS, RETURN, SPACE])
    }

    @Test
    @DisplayName("Should Type Key Modifiers")
    void keyModifier() {
        keyboard.type([SHIFT, 'tyro'])

        verify(provider, times(1)).type([SHIFT, 'tyro'])
    }
}
