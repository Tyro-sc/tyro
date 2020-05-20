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

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ById byId = (ById) o
            id == byId.id
        }

        int hashCode() {
            return id.hashCode()
        }
    }

    public static class ByExpression extends By {
        public final String expression

        public ByExpression(String expression) {
            this.expression = expression
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByExpression that = (ByExpression) o
            expression == that.expression
        }

        int hashCode() {
            return expression.hashCode()
        }
    }

    public static class ByValue extends By {
        public String value

        ByValue(String value) {
            this.value = value;
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByValue byValue = (ByValue) o
            value == byValue.value
        }

        int hashCode() {
            return value.hashCode()
        }
    }

    public static class ByText extends By {
        public String text

        ByText(String text) {
            this.text = text;
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByText byText = (ByText) o
            text == byText.text
        }

        int hashCode() {
            return text.hashCode()
        }
    }

    public static class ByLabel extends By {
        public String label

        ByLabel(String label) {
            this.label = label;
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByLabel byLabel = (ByLabel) o
            label == byLabel.label
        }

        int hashCode() {
            return label.hashCode()
        }
    }

    public static class ByTitle extends By {
        public String title

        ByTitle(String title) {
            this.title = title;
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            ByTitle byTitle = (ByTitle) o
            title == byTitle.title
        }

        int hashCode() {
            return title.hashCode()
        }
    }
}