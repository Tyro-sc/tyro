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
        browser = new Browser(provider)
    }

    @Test
    @DisplayName("Should Open an URL")
    void open() {
        browser.open('https://shouldItestprivatemethods.com')

        verify(provider, times(1)).open('https://shouldItestprivatemethods.com')
    }

    @Test
    @DisplayName("Should Navigate to")
    void navigate() {
        browser.navigateTo('https://someUrl')

        verify(provider, times(1)).navigateTo('https://someUrl')
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
        when(provider.url).thenReturn('https://www.tyro.sc')

        assertThat(browser.url, is('https://www.tyro.sc'))
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
        when(provider.windowIds).thenReturn(Set.of('id_1', 'id_2', 'id_3'))

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
