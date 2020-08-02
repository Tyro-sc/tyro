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
package sc.tyro.core

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Browser {
    private final Provider provider

    Browser(Provider provider) {
        this.provider = provider
    }

    void open(String url) { provider.open(url) }

    void navigateTo(String url) { provider.navigateTo(url) }

    void back() { provider.back() }

    void forward() { provider.forward() }

    void refresh() { provider.refresh() }

    String getTitle() { provider.pageTitle }

    String getUrl() { provider.url }

    List<Window> getWindows() {
        List<Window> windows = new ArrayList<>()
        provider.windowIds.each { String id ->
            windows.add(new Window(id, provider))
        }
        return windows
    }

    void switchTo(Window window) { provider.switchToWindow(window.id) }
}
