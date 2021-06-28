package sc.tyro.core.component

import sc.tyro.core.support.Selectable
import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.property.SelectedItemSupport
import sc.tyro.core.support.state.EmptySupport

abstract class Combobox extends Component
        implements LabelSupport, EmptySupport, Selectable,
                ItemSupport, SelectedItemSupport {
}