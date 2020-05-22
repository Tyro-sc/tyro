package sc.tyro.core

public abstract class By {
    public static ById id(String id) {
        new ById(id)
    }

    public static ByExpression expression(String expression) {
        new ByExpression(expression)
    }

    public static ByValue value(String value) {
        new ByValue(value);
    }

    public static ByText text(String text) {
        new ByText(text);
    }

    public static ByLabel label(String label) {
        new ByLabel(label)
    }

    public static ByTitle title(String title) {
        new ByTitle(title)
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

    public static class ByValue extends By {
        public String value

        ByValue(String value) {
            this.value = value;
        }

        @Override
        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByValue byValue = (ByValue) o
            value == byValue.value
        }

        @Override
        int hashCode() {
            return value.hashCode()
        }

        @Override
        String toString() {
            return 'value: ' + value
        }
    }

    public static class ByText extends By {
        public String text

        ByText(String text) {
            this.text = text;
        }

        @Override
        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByText byText = (ByText) o
            text == byText.text
        }

        @Override
        int hashCode() {
            return text.hashCode()
        }

        @Override
        String toString() {
            return 'text: ' + text
        }
    }

    public static class ByLabel extends By {
        public String label

        ByLabel(String label) {
            this.label = label
        }

        @Override
        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByLabel byLabel = (ByLabel) o
            label == byLabel.label
        }

        @Override
        int hashCode() {
            return label.hashCode()
        }

        @Override
        String toString() {
            return 'label: ' + label
        }
    }

    public static class ByTitle extends By {
        public String title

        ByTitle(String title) {
            this.title = title;
        }

        @Override
        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByTitle byTitle = (ByTitle) o
            title == byTitle.title
        }

        @Override
        int hashCode() {
            return title.hashCode()
        }

        @Override
        String toString() {
            return 'title: ' + title
        }
    }
}