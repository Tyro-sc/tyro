/**
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
import sc.tyro.core.component.ListBox
import sc.tyro.web.CssIdentifier

import static sc.tyro.bundle.html5.input.Label.findFor

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier("select[multiple]")
class MultiSelect extends ListBox {
    @Override
    List<Option> items() {
        provider.findAll(By.expression('#' + id() + ' option'), Option)
    }

    @Override
    Option item(String value) {
        items().find { it.value() == value }
    }

    @Override
    List<Option> visibleItems() {
        int size = provider.eval(id(), "it.prop('size')") as Integer
        items()[0..size - 1]
    }

    @Override
    List<OptionGroup> groups() {
        provider.findAll(By.expression('#' + id() + ' optgroup'), OptionGroup)
    }

    @Override
    OptionGroup group(String value) {
        groups().find { it.value() == value }
    }

    List<Option> selectedItems() {
        items().findAll { it.selected() }
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
