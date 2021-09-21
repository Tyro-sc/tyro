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
import sc.tyro.core.support.property.LabelSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
class LabelMatcher extends PropertyMatcher<LabelSupport> {
    private String label

    LabelMatcher(String label) {
        this.label = label
    }

    @Override
    protected boolean matchesSafely(LabelSupport component) {
        component.label() == label
    }

    @Override
    void describeTo(Description description) {
        description.appendText('label ') appendValue(label)
    }

    @Override
    protected void describeMismatchSafely(LabelSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has label ').appendValue(component.label())
    }
}
