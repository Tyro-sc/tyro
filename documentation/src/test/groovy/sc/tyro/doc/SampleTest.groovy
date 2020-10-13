package sc.tyro.doc

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import static sc.tyro.core.Tyro.*

@ExtendWith(TyroExtension)
@DisplayName("Sample 1")
class SampleTest {
    @Test
    void sample_1() {
        visit 'http://localhost:8080/sample_1.html'

        // tag::sample_1[]
        field('Email').should {
            be visible
            be empty
        }

        field('Password').should {
            be visible
            be empty
        }

        dropdown('Language').should {
            be visible
            have 2.items
            have items('EN', 'FR')
            have selectedItem('EN')
        }
        // end::sample_1[]
    }

    @Test
    void sample_2() {
        visit 'http://localhost:8080/sample_2.html'

        // tag::sample_2[]
        radio("Male").should { be unchecked }
        radio("Female").should { be checked }

        check radio("Male")

        radio("Male").should { be checked }
        radio("Female").should { be unchecked }
        // end::sample_2[]
    }
}