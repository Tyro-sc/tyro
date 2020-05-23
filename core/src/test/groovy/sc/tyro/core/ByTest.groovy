package sc.tyro.core

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("By Tests")
class ByTest {
    @Test
    @DisplayName("Should instantiate ById")
    void byId() {
        By.ById by = By.id('id')

        assertThat(by.id, is('id'))
        assertThat(by.hashCode(), is('id'.hashCode()))
        assertThat(by.toString(), is('id: id'))
    }

    @Test
    @DisplayName("Should instantiate ByExpression")
    void byExpression() {
        By.ByExpression by = By.expression('expression')

        assertThat(by.expression, is('expression'))
        assertThat(by.hashCode(), is('expression'.hashCode()))
        assertThat(by.toString(), is('expression: expression'))
    }

    @Test
    @DisplayName("Should instantiate ByValue")
    void byValue() {
        By.ByValue by = By.value('value')

        assertThat(by.value, is('value'))
        assertThat(by.hashCode(), is('value'.hashCode()))
        assertThat(by.toString(), is('value: value'))
    }

    @Test
    @DisplayName("Should instantiate ByText")
    void byText() {
        By.ByText by = By.text('text')

        assertThat(by.text, is('text'))
        assertThat(by.hashCode(), is('text'.hashCode()))
        assertThat(by.toString(), is('text: text'))
    }

    @Test
    @DisplayName("Should instantiate ByLabel")
    void byLabel() {
        By.ByLabel by = By.label('label')

        assertThat(by.label, is('label'))
        assertThat(by.hashCode(), is('label'.hashCode()))
        assertThat(by.toString(), is('label: label'))
    }

    @Test
    @DisplayName("Should instantiate ByTitle")
    void byTitle() {
        By.ByTitle by = By.title('title')

        assertThat(by.title, is('title'))
        assertThat(by.hashCode(), is('title'.hashCode()))
        assertThat(by.toString(), is('title: title'))
    }

    @Test
    @DisplayName("Should have equality on id for ById")
    void byIdEquality() {
        By.ById by_1 = By.id('id_1')
        By.ById by_2 = By.id('id_2')
        By.ById by_3 = By.id('id_1')

        assertThat(by_1, is(not(equalTo(by_2))))
        assertThat(by_1, is(equalTo(by_3)))
    }
}