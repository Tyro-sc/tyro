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
package sc.tyro.core.hamcrest.matcher.property

import org.hamcrest.Description
import sc.tyro.core.hamcrest.PropertyMatcher
import sc.tyro.core.support.property.CellSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class CellSizeMatcher extends PropertyMatcher<CellSupport> {
    private Integer number

    CellSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(CellSupport component) {
        component.cells().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' cell(s)')
    }

    @Override
    protected void describeMismatchSafely(CellSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.cells().size()).appendText(' cell(s)')
    }
}
