package sc.tyro.core.component

import sc.tyro.core.support.Selectable
import sc.tyro.core.support.property.GroupSupport
import sc.tyro.core.support.property.ItemSupport
import sc.tyro.core.support.property.LabelSupport
import sc.tyro.core.support.property.SelectedItemSupport
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public abstract class Dropdown extends Component
        implements LabelSupport, GroupSupport, EmptySupport, Selectable,
                ItemSupport, SelectedItemSupport {
}