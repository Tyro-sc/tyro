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
package sc.tyro.bundle.html5

import sc.tyro.core.component.Component
import sc.tyro.core.support.property.MaximumSupport
import sc.tyro.core.support.property.ValueSupport
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('progress')
class Progress extends Component implements MaximumSupport, ValueSupport {
    @Override
    Object maximum() {
        provider.eval(id(), "it.prop('max')") as BigDecimal
    }

    @Override
    Object value() {
        provider.eval(id(), "it.prop('value')") as BigDecimal
    }
}
