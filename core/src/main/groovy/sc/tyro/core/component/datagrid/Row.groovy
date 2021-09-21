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
package sc.tyro.core.component.datagrid

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.CellSupport
import sc.tyro.core.support.property.TitleSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
abstract class Row extends Component implements TitleSupport, CellSupport {
    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (!o in Row) return false
        Row row = (Row) o
        title() == row.title()
    }

    @Override
    int hashCode() { title().hashCode() }
}