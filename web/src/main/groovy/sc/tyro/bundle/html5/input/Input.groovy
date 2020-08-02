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
package sc.tyro.bundle.html5.input

import sc.tyro.core.ComponentException

import static sc.tyro.bundle.html5.input.Label.findFor
import static sc.tyro.core.Tyro.type
import static sc.tyro.core.input.Key.BACK_SPACE
import static sc.tyro.core.input.Key.CTRL

/**
 * @author David Avenante
 * @since 1.0.0
 */
trait Input {
    String placeholder() {
        provider.eval(id(), "it.prop('placeholder')")
    }

    boolean empty() {
        provider.check(id(), "\$.trim(it.val()).length == 0")
    }

    boolean readOnly() {
        provider.check(id(), "it.prop('readonly')")
    }

    boolean required() {
        provider.check(id(), "it.prop('required')")
    }

    boolean focused() {
        provider.check(id(), "it.is(':focus')")
    }

    void value(Object value) {
        if (!this.enabled()) {
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be filled")
        }
        this.click()
        provider.runScript("\$('[id=\"${id()}\"]').val('')")
        type([String.valueOf(value)])
    }

    String label() {
        findFor(this)
    }

    void clear() {
        this.click()
        type(CTRL + 'a')
        type(BACK_SPACE)
    }

    Object value() {
        provider.eval(id(), "it.val()")
    }

    boolean valid() {
        provider.check(id(), "it[0].validity.valid")
    }

    Number length() {
        BigDecimal length = provider.eval(id(), "it.prop('maxlength')") as BigDecimal
        if (length.signum() == -1) {
            throw new ComponentException("Not length defined for component ${this.class.simpleName} ${this}")
        }
        length
    }
}