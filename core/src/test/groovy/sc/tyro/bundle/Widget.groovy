package sc.tyro.bundle

import sc.tyro.core.component.Component

@AnIdentifier
class Widget extends Component {
    @Override
    String toString() {
        return 'widget:' + this.id()
    }
}
