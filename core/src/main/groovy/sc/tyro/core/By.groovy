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
package sc.tyro.core

/**
 * @author David Avenante
 * @since 1.0.0
 */
class By {
    static ById id(String id) {
        new ById(id)
    }

    static ByExpression expression(String expression) {
        new ByExpression(expression)
    }

    static class ById extends By {
        final String id

        ById(String id) {
            this.id = id
        }

        @Override
        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false
            ById byId = (ById) o
            id == byId.id
        }

        @Override
        int hashCode() {
            return id.hashCode()
        }

        @Override
        String toString() {
            return 'id: ' + id
        }
    }

    static class ByExpression extends By {
        final String expression

        ByExpression(String expression) {
            this.expression = expression
        }

        @Override
        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false
            ByExpression that = (ByExpression) o
            expression == that.expression
        }

        @Override
        int hashCode() {
            return expression.hashCode()
        }

        @Override
        String toString() {
            return 'expression: ' + expression
        }
    }
}