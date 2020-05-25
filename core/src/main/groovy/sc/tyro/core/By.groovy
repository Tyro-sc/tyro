package sc.tyro.core

public class By {
    public static ById id(String id) {
        new ById(id)
    }

    public static ByExpression expression(String expression) {
        new ByExpression(expression)
    }

    public static class ById extends By {
        public final String id

        public ById(String id) {
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

    public static class ByExpression extends By {
        public final String expression

        public ByExpression(String expression) {
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