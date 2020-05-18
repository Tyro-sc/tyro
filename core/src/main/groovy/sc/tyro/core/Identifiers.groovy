package sc.tyro.core

import sc.tyro.core.component.Component

interface Identifiers {
    boolean hasIdentifier(Class<? extends Component> c)
}