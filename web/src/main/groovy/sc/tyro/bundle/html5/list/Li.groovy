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

import sc.tyro.core.ComponentException
import sc.tyro.core.component.Item
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('li')
class Li extends Item {
    String value() {
        provider.eval(id(), 'it.text().trim()')
    }

    boolean selected() {
        throw new ComponentException('Unsupported Operation')
    }

    void select() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be selected (Unsupported Operation)")
    }

    void unselect() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be unselected (Unsupported Operation)")
    }

    @Override
    boolean equals(Object o) {
        if (this.is(o)) return true
        return value() == ((Li) o).value()
    }

    @Override
    int hashCode() {
        return value().hashCode()
    }

    @Override
    String toString() {
        return value()
    }
}
