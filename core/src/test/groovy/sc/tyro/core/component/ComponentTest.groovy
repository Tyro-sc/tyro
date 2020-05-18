package sc.tyro.core.component

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.ComponentException
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.support.Draggable
import sc.tyro.core.support.MouseSupport

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static sc.tyro.core.Config.provider

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Component Tests")
class ComponentTest {
    @BeforeEach
    void before() {
        provider = mock(Provider)
    }

    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Component in MouseSupport
        assert Component in Draggable
    }

    @Test
    @DisplayName("Should have identity on Id")
    void identity() {
        Component cmp_1 = new Component()
        Component cmp_2 = new Component()
        Component cmp_3 = new Component()
        Component cmp_4 = new Button() {
            @Override
            String text() {
                return "Button"
            }
        }

        when(provider.metaInfo(any(Component)))
                .thenReturn(new MetaInfo(id: '1'))
                .thenReturn(new MetaInfo(id: '2'))
                .thenReturn(new MetaInfo(id: '1'))
                .thenReturn(new MetaInfo(id: '1'))

        assert cmp_1 != cmp_2 // Same class not same id
        assert cmp_1 == cmp_3  // Same class and same id
        assert cmp_1 != cmp_4  // Different class and same id

        assert cmp_1.hashCode() == cmp_1.id().hashCode() // hashCode is based on id
    }

    @Test
    @DisplayName("Should implement toString() based on ClassName and Id")
    void implementToString() {
        Component cmp_1 = new Component()

        when(provider.metaInfo(cmp_1)).thenReturn(new MetaInfo(id: '1'))

        assert cmp_1.toString() == 'Component:1'
    }

    @Test
    @DisplayName("Should be available")
    void available() {
        Component cmp = new Component()
        cmp.provider = provider

        when(provider.metaInfo(cmp)).thenReturn(new MetaInfo('node', '1'))

        assert cmp.available()

        when(provider.metaInfo(cmp)).thenThrow(new ComponentException(""))

        assert !cmp.available()
    }

    @Test
    @DisplayName("Should implements state: enabled")
    void enabled() {
        Component cmp_1 = new Component()

        when(provider.enabled(cmp_1)).thenReturn(false)

        assert !cmp_1.enabled()
    }

    @Test
    @DisplayName("Should implements state: visible")
    void visible() {
        Component cmp_1 = new Component()

        when(provider.visible(cmp_1)).thenReturn(true)

        assert cmp_1.visible()
    }

    @Test
    @DisplayName("Should implements contains")
    void contains() {
        Component cmp_1 = new Component()
        Component cmp_2 = new Component()

        when(provider.contains(cmp_2)).thenReturn(true)

        assert cmp_1.contains(cmp_2)
    }

    @Test
    @DisplayName("Should be draggable")
    void draggable() {
        Component cmp_1 = new Component()
        cmp_1.provider = provider
        Component cmp_2 = new Component()

        cmp_1.drag().on(cmp_2)

        verify(provider, times(1)).dragAndDrop(cmp_1, cmp_2)
    }

    @Test
    @DisplayName("Should support type coercion")
    void coercion() {
        Component cmp_1 = new Widget()

        Widget cmp_2 = cmp_1 as Widget
        assert !cmp_1.is(cmp_2)
        // Provider is passed to new Object
        assert cmp_1.provider.is(cmp_2.provider)
    }

    private class Widget extends Component {}
}
