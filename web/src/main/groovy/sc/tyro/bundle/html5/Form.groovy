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
package sc.tyro.bundle.html5


import sc.tyro.core.By
import sc.tyro.core.ComponentException
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('form')
class Form extends sc.tyro.core.component.Form {
    @Override
    void reset() {
        Button reset_button = provider.find(By.expression('#' + id() + ' [type=reset]:first'), Button)
        if (reset_button && reset_button.available())
            reset_button.click()
        else
            throw new ComponentException('Cannot reset form without reset button')
    }

    @Override
    void submit() {
        Button submit_button = provider.find(By.expression('#' + id() + ' [type=submit]:first'), Button)
        if (submit_button && submit_button.available())
            submit_button.click()
        else
            throw new ComponentException('Cannot submit form without submit button')
    }

    @Override
    boolean valid() {
        provider.findAll(By.expression('#' + id() + ' input'), Component).findAll { input ->
            provider.check(input.id(), "it.is(':invalid')")
        }.empty
    }
}
