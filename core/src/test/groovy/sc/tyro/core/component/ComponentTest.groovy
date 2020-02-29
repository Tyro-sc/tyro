package sc.tyro.core.component

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.Config
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.support.Draggable
import sc.tyro.core.support.MouseSupport

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
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
    void should_have_expected_inheritance() {
        assert Component in MouseSupport
        assert Component in Draggable
    }

    @Test
    @DisplayName("Should have identity on Id")
    void should_have_identity_on_id() {
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
    void should_implement_toString_based_on_class_name_and_id() {
        Component cmp_1 = new Component()

        when(provider.metaInfo(cmp_1)).thenReturn(new MetaInfo(id: '1'))

        assert cmp_1.toString() == 'Component:1'
    }

//    @Test
//    @DisplayName("")
//    void should_have_generic_behaviours_delegated_to_provider() {
//
//    }

}
