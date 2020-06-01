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

    void navigateTo(String  url) { provider.navigateTo(url) }

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
