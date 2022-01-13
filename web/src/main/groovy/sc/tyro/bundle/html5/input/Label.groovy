/*
 * Copyright © 2021 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
