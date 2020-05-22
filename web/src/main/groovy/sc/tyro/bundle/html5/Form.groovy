package sc.tyro.bundle.html5


import sc.tyro.core.By
import sc.tyro.core.ComponentException
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('form')
class Form extends sc.tyro.core.component.Form {
    @Override
    void reset() {
        Button reset_button = provider.find(By.expression('[type=reset]:first'), Button)
        if (reset_button && reset_button.available())
            reset_button.click()
        else
            throw new ComponentException('Cannot reset form without reset button')
    }

    @Override
    void submit() {
        Button submit_button = provider.find(By.expression('[type=submit]:first'), Button)
        if (submit_button && submit_button.available())
            submit_button.click()
        else
            throw new ComponentException('Cannot submit form without submit button')
    }

    @Override
    boolean valid() {
        provider.findAll(By.expression('#' + id() + ' input'), Component).findAll { input ->
            provider.check(input.id(), "it.is(':invalid')")
        }.empty
    }
}
