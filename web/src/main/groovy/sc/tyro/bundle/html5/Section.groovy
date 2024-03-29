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

import sc.tyro.core.By
import sc.tyro.core.component.Component
import sc.tyro.web.CssIdentifier

/**
 * @author David Avenante
 * @since 1.0.0
 */
@CssIdentifier('section')
class Section extends Component {
    List<P> paragraphs() {
        provider.findAll(P, By.expression('[id="' + id() + '"] > p'))
    }

    List<Article> articles() {
        provider.findAll(Article, By.expression('[id="' + id() + '"] > article'))
    }
}
