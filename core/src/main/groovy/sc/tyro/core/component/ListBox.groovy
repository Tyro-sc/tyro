package sc.tyro.core.component

import sc.tyro.core.support.Selectable
import sc.tyro.core.support.UnSelectable
import sc.tyro.core.support.property.*
import sc.tyro.core.support.state.EmptySupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public abstract class ListBox extends Component implements ItemSupport, GroupSupport, SelectedItemsSupport, EmptySupport,
        VisibleItemsSupport, LabelSupport, Selectable, UnSelectable {}