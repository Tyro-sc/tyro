package sc.tyro.dsl

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.Provider
import sc.tyro.core.component.Button
import sc.tyro.core.component.Heading
import sc.tyro.core.component.Link

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.Config.provider
import static sc.tyro.core.Tyro.available
import static sc.tyro.core.Tyro.button
import static sc.tyro.core.Tyro.heading
import static sc.tyro.core.Tyro.link
import static sc.tyro.core.hamcrest.Matchers.has
import static sc.tyro.core.hamcrest.Matchers.items

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Factory Tests")
class FactoryTest {
    @BeforeEach
    void setUp() {
        provider = mock(Provider)
    }

    @Test
    @DisplayName("Should find button by text")
    void findButtonByText() {
        Button button_1 = mock(Button)
        when(button_1.text()).thenReturn('Ok')
        Button button_2 = mock(Button)
        when(button_2.text()).thenReturn('!Ok')

        when(provider.findBy(Button)).thenReturn(List.of(button_1, button_2))

        button("Ok").should { be available }

        // Should fail if more than on match
        when(button_2.text()).thenReturn('Ok')


        IllegalStateException error = assertThrows(IllegalStateException, { button("Ok").should { be available } })
        assertThat(error.message, is("Find many components Button with text 'Ok'."))
    }

    @Test
    @DisplayName("Should find heading by text")
    void findHeadingByText() {
        Heading heading_1 = mock(Heading)
        when(heading_1.text()).thenReturn('Title')
        Heading heading_2 = mock(Heading)
        when(heading_2.text()).thenReturn('!Title')

        when(provider.findBy(Heading)).thenReturn(List.of(heading_1, heading_2))

        heading("Title").should { be available }

        // Should fail if more than on match
        when(heading_2.text()).thenReturn('Title')


        IllegalStateException error = assertThrows(IllegalStateException, { heading("Title").should { be available } })
        assertThat(error.message, is("Find many components Heading with text 'Title'."))
    }

    @Test
    @DisplayName("Should find link by text")
    void findLinkByText() {
        Link link_1 = mock(Link)
        when(link_1.text()).thenReturn('Link')
        Link link_2 = mock(Link)
        when(link_2.text()).thenReturn('!Link')

        when(provider.findBy(Link)).thenReturn(List.of(link_1, link_2))

        link("Link").should { be available }

        // Should fail if more than on match
        when(link_2.text()).thenReturn('Link')

        IllegalStateException error = assertThrows(IllegalStateException, { link("Link").should { be available } })
        assertThat(error.message, is("Find many components Link with text 'Link'."))
    }
}
