package sc.tyro.doc

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.core.component.Radio

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static sc.tyro.core.Tyro.*

@Disabled
class SpecTest {
    @Test
    @DisplayName("Gender spec")
    void gender() {
        Radio male_radio = mock(Radio)
        Radio female_radio = mock(Radio)

        when(male_radio.checked()).thenReturn(false, true, false)
        when(male_radio.label()).thenReturn('Male')
        when(male_radio.enabled()).thenReturn(true)

        when(female_radio.checked()).thenReturn(false, false, true)
        when(female_radio.label()).thenReturn('Female')
        when(female_radio.enabled()).thenReturn(true)

        // tag::gender-spec[]
        male_radio.should {
            be unchecked
            have label('Male')
        }

        female_radio.should {
            be unchecked
            have label('Female')
        }

        check male_radio
        male_radio.should { be checked }
        female_radio.should { be unchecked }

        check female_radio
        male_radio.should { be unchecked }
        female_radio.should { be checked }
        // end::gender-spec[]
    }
}