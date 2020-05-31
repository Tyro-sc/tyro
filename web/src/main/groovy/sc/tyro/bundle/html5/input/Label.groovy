package sc.tyro.bundle.html5.input

import sc.tyro.core.Config
import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Label {
    private static final String expr =
        "function() {" +
            "   var label = \$('label[for=' + it.attr('id') + ']');" +
            "   if (label.length > 0) return label.text().trim();" +
            "   var p = it.prev('label');" +
            "   if (p.length > 0) return p.text();" +
            "   return it.parent().text().trim();" +
            "}()"

    static String findFor(Component c) {
        Config.provider.eval(c.id(), expr).trim()
    }
}
