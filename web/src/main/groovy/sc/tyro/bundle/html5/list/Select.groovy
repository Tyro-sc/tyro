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
package sc.tyro.bundle.html5.list

import sc.tyro.bundle.html5.input.Validity
import sc.tyro.core.By
import sc.tyro.core.component.Dropdown
import sc.tyro.web.CssIdentifier

import static sc.tyro.bundle.html5.input.Label.findFor

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier("select:not([multiple])")
class Select extends Dropdown implements Validity {
    @Override
    List<Option> items() {
        provider.findAll(Option, By.expression('[id="' + id() + '"] option'))
    }

    @Override
    List<OptionGroup> groups() {
        provider.findAll(OptionGroup, By.expression('[id="' + id() + '"] optgroup'))
    }

    @Override
    OptionGroup group(String value) {
        groups().find { it.value() == value }
    }

    @Override
    Option item(String value) {
        items().find { it.value() == value }
    }

    @Override
    String label() {
        findFor(this)
    }

    @Override
    boolean empty() {
        items().empty
    }
}
