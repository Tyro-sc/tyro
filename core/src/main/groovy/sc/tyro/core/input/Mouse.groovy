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
package sc.tyro.core.input

import sc.tyro.core.component.Component

/**
 * @author David Avenante
 * @since 1.0.0
 */
class Mouse {
    static void clickOn(Component c) { c.click() }

    static void doubleClickOn(Component c) { c.doubleClick() }

    static void rightClickOn(Component c) { c.rightClick() }

    static void hoveringMouseOn(Component c) { c.mouseOver() }

    static DragBuilder drag(Component c) { return new DragBuilder(c) }
}
