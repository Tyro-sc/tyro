package sc.tyro.core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Browser Tests")
class BrowserTest {
    private Provider provider
    private Browser browser

    @BeforeEach
    void setUp() {
        provider = mock(Provider)
        Config.provider = provider
        browser = new Browser(provider)
    }

    @Test
    @DisplayName("Should Open an URL")
    void navigate() {
        browser.open('http://shouldItestprivatemethods.com')

        verify(provider, times(1)).open('http://shouldItestprivatemethods.com')
    }

    @Test
    @DisplayName("Should Navigate back")
    void back() {
        browser.back()

        verify(provider, times(1)).back()
    }

    @Test
    @DisplayName("Should Navigate forward")
    void forward() {
        browser.forward()

        verify(provider, times(1)).forward()
    }

    @Test
    @DisplayName("Should Refresh")
    void refresh() {
        browser.refresh()

        verify(provider, times(1)).refresh()
    }

    @Test
    @DisplayName("Should get URL")
    void getUrl() {
        when(provider.url).thenReturn('http://www.tyro.sc')

        assertThat(browser.url, is('http://www.tyro.sc'))
    }

    @Test
    @DisplayName("Should get Title")
    void getTitle() {
        when(provider.pageTitle).thenReturn('Tyro Rocks')

        assertThat(browser.title, is('Tyro Rocks'))
    }

    @Test
    @DisplayName("Should get all windows")
    void windows() {
        when(provider.windowIds).thenReturn(List.of('id_1', 'id_2', 'id_3'))

        assertThat(browser.windows, hasSize(3))
    }

    @Test
    @DisplayName("Should switch to specific window")
    void switchToWindow() {
        Window window = new Window('id', provider)

        browser.switchTo(window)

        verify(provider, times(1)).switchToWindow('id')
    }
}
