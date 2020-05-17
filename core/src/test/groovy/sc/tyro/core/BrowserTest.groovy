package sc.tyro.core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

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
    @DisplayName("Should Navigate to an URL")
    void navigate() {
        browser.navigateTo('http://shouldItestprivatemethods.com')

        verify(provider, times(1)).navigateTo('http://shouldItestprivatemethods.com')
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

}
