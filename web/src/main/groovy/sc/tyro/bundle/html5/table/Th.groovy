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
package sc.tyro.bundle.html5.table

import sc.tyro.core.By
import sc.tyro.core.component.datagrid.Column
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('th')
class Th extends Column {
    @Override
    List<Td> cells() {
        int index = provider.eval(id(), "it.index() + 1") as int
        provider.findAll(By.expression("\$('[id=\"${id()}\"]').closest('table').find('tbody tr').find('td:nth-child(${index})')"), Td)
    }

    @Override
    Td cell(Object value) {
        cells().find { it.value() == value }
    }

    @Override
    String title() {
        provider.eval(id(), "it.text().trim()")
    }
}
