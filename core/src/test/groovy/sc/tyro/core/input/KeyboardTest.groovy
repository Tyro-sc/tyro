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
