/*
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
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
package sc.tyro.bundle.html5.list

import sc.tyro.core.By
import sc.tyro.core.component.Group
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('optgroup')
class OptionGroup extends Group {
    List<Option> items() {
        provider.findAll(Option, By.expression('#' + id() + ' option'))
    }

    Option item(String value) {
        items().find { it.value() == value }
    }

    String value() {
        provider.eval(id(), "it.attr('label')")
    }
}
