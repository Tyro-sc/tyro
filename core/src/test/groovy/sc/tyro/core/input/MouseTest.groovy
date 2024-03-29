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
package sc.tyro.core.input

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.MetaDataProvider
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.component.Component

import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider
import static sc.tyro.core.input.Key.ALT
import static sc.tyro.core.input.Key.CTRL
import static sc.tyro.core.input.Key.DELETE
import static sc.tyro.core.input.Key.SPACE
import static sc.tyro.core.input.MouseModifiers.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Mouse Interactions Tests")
class MouseTest {
    private Mouse mouse = new Mouse()
    private Component cmp

    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        MetaDataProvider meta = mock(MetaDataProvider)
        cmp = new Component(provider, meta)

        when(meta.metaInfo(cmp)).thenReturn(new MetaInfo(node: 'node', id: '1'))
    }

    @AfterEach
    void tearDown() {
        reset(provider)
    }

    @Test
    @DisplayName("Should Click")
    void click() {
        mouse.clickOn(cmp)

        verify(provider, times(1)).click(cmp, [LEFT, SINGLE], [])
    }

    @Test
    @DisplayName("Should Double Click")
    void doubleClick() {
        mouse.doubleClickOn(cmp)

        verify(provider, times(1)).click(cmp, [LEFT, DOUBLE], [])
    }

    @Test
    @DisplayName("Should right click")
    void rightClick() {
        mouse.rightClickOn(cmp)

        verify(provider, times(1)).click(cmp, [RIGHT, SINGLE], [])
    }

    @Test
    @DisplayName("Should Mouse Over")
    void mouseOver() {
        mouse.hoveringMouseOn(cmp)

        verify(provider, times(1)).mouseOver(cmp)
    }

    @Test
    @DisplayName("Should drag and drop")
    void dragAndDrop() {
        Component cmpTarget = spy(new Component())

        mouse.drag(cmp).on(cmpTarget)

        verify(provider, times(1)).dragAndDrop(cmp, cmpTarget)
    }

    @Test
    @DisplayName("Should use Mouse with Key Modifier")
    void mouseWithKeyModifier() {
        CTRL.click cmp
        verify(provider, times(1)).click(cmp, [LEFT, SINGLE], [CTRL])

        [CTRL + ALT + DELETE].click cmp
        verify(provider, times(1)).click(cmp, [LEFT, SINGLE], [CTRL + ALT + DELETE])

        [SPACE].click cmp
        verify(provider, times(1)).click(cmp, [LEFT, SINGLE], [SPACE])

        CTRL.rightClick cmp
        verify(provider, times(1)).click(cmp, [RIGHT, SINGLE], [CTRL])

        [CTRL + ALT + DELETE].rightClick cmp
        verify(provider, times(1)).click(cmp, [RIGHT, SINGLE], [CTRL + ALT + DELETE])

        [SPACE].rightClick cmp
        verify(provider, times(1)).click(cmp, [RIGHT, SINGLE], [SPACE])
    }
}
