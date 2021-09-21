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
import sc.tyro.core.support.property.ColumnSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class ColumnSizeMatcher extends PropertyMatcher<ColumnSupport> {
    private Integer number

    ColumnSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(ColumnSupport component) {
        component.columns().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' column(s)')
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.columns().size()).appendText(' column(s)')
    }
}
