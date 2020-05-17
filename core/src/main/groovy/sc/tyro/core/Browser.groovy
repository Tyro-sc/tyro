package sc.tyro.core

import sc.tyro.core.component.Window

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Browser {
    private final Provider provider

    Browser(Provider provider) {
        this.provider = provider
    }

    void navigateTo(String url) { provider.navigateTo(url) }

    void back() { provider.back() }

    void forward() { provider.forward() }

    void refresh() { provider.refresh() }

    String getTitle() { provider.title }

    String getUrl() { provider.url }

    //TODO: duplicate with NavigateTo ?
    void open(String url) { provider.open(url) }

    List<Window> getWindows() {
        List<Window> windows = new ArrayList<>()
        provider.windowIds.each { String id ->
            windows.add(new Window(id))
        }
        return windows
    }

    void switchTo(Window window) { provider.switchToWindow(window.id) }
}
