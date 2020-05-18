package sc.tyro.core.input

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.component.Component

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider
import static sc.tyro.core.input.Key.CTRL
import static sc.tyro.core.input.Key.ALT
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
//        when(provider.metaInfo(any())).thenReturn(new MetaInfo('node', '1'))

        cmp = new Component()
    }

    @AfterEach
    void tearDown() {
        reset(provider)
    }

    @Test
    @DisplayName("Should Click")
    void should_be_able_to_click() {
        mouse.clickOn(cmp)

        verify(provider, times(1)).click(cmp, [LEFT, SINGLE], [])
    }

    @Test
    @DisplayName("Should Double Click")
    void should_be_able_to_doubleClick() {
        mouse.doubleClickOn(cmp)

        verify(provider, times(1)).click(cmp, [LEFT, DOUBLE], [] as Collection<?>)
    }

    @Test
    @DisplayName("Should right click")
    void should_be_able_to_rightClick() {
        mouse.rightClickOn(cmp)

        verify(provider, times(1)).click(cmp, [RIGHT, SINGLE], [])
    }

    @Test
    @DisplayName("Should Mouse Over")
    void should_be_able_to_mouseOver() {
        mouse.hoveringMouseOn(cmp)

        verify(provider, times(1)).mouseOver(cmp)
    }

    @Test
    @DisplayName("Should drag and drop")
    void should_be_able_to_dragAndDrop() {
        Component cmpTarget = spy(new Component())

        mouse.drag(cmp).on(cmpTarget)

        verify(provider, times(1)).dragAndDrop(cmp, cmpTarget)
    }

    @Test
    @DisplayName("Should use Mouse with Key Modifier")
    void should_be_able_to_use_mouse_with_key_modifier() {
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
