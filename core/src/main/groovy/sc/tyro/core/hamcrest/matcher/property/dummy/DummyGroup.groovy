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
package sc.tyro.core.hamcrest.matcher.property.dummy

import sc.tyro.core.component.Group
import sc.tyro.core.component.Item

/**
 * @author David Avenante
 * @since 1.0.0
 */
class DummyGroup extends Group {
    String value

    DummyGroup(String value) {
        this.value = value
    }

    @Override
    List<Item> items() {
        return []
    }

    @Override
    Item item(String value) {
        return null
    }

    @Override
    String value() {
        return value
    }
}
