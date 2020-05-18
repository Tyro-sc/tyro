package sc.tyro.bundle.html5

import sc.tyro.core.Config
import sc.tyro.web.CssIdentifier

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
@CssIdentifier('button,input[type=submit],input[type=button],input[type=reset],input[type=image]')
class Button extends sc.tyro.core.component.Button {
    @Override
    String text() {
        Config.provider.eval(this.id(), "it.is('input') ? it.val() : it.text().trim()")
    }
}
