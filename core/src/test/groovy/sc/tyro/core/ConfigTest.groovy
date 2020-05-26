package sc.tyro.core

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.bundle.Widget

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@DisplayName("Configuration Tests")
class ConfigTest {
    @BeforeAll
    static void before() {
        Config.provider = null
        Config.identifiers = null
    }

    @Test
    @DisplayName("Should set the provider and Identifier")
    void provider() {
        assertThat(Config.provider, is(nullValue()))
        assertThat(Config.identifiers, is(nullValue()))

        Provider provider = mock(Provider)
        Identifiers identifiers = mock(Identifiers)

        Config.provider = provider
        Config.identifiers = identifiers

        assertThat(Config.provider, is(equalTo(provider)))
        assertThat(Config.identifiers, is(equalTo(identifiers)))
    }

    @Test
    @DisplayName("Should change the waitUntil Duration")
    void setDuration() {
        assertThat(Config.waitUntil, is(equalTo(2.seconds)))

        Config.waitUntil = 10.seconds

        assertThat(Config.waitUntil, is(equalTo(10.seconds)))
    }

    @Test
    @DisplayName("Should add Components")
    void addComponents() {
        assertThat(Config.componentTypes, is(empty()))

        Identifiers identifiers = mock(Identifiers)
        when(identifiers.hasIdentifier(Widget)).thenReturn(true)
        Config.identifiers = identifiers;

        Config.scan("sc.tyro.bundle")

        assertThat(Config.componentTypes, hasSize(1))
    }
}
