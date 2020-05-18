package sc.tyro.core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Window Tests")
class WindowTest {
    private Provider provider

    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        Config.provider = provider
    }

    @Test
    @DisplayName("Should instantiate a window")
    void create() {
        Window window = new Window('id', provider)

        assertThat(window.id, is('id'))
        assertThat(window.id.hashCode(), is('id'.hashCode()))
    }

    @Test
    @DisplayName("Should have equality and hasCode based on id")
    void equality() {
        Window window_1 = new Window('id_1', provider)
        Window window_2 = new Window('id_2', provider)
        Window window_3 = new Window('id_1', provider)

        assertThat(window_1, is(not(equalTo(window_2))))
        assertThat(window_1, is(equalTo(window_3)))

        assertThat(window_1.hashCode(), is(not(window_2.hashCode())))
        assertThat(window_1.hashCode(), is(window_3.hashCode()))
    }

    @Test
    @DisplayName("Should close window")
    void close() {
        Window window = new Window('id', provider)

        window.close()

        verify(provider, times(1)).closeWindow('id')
    }
}
