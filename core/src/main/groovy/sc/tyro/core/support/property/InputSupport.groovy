package sc.tyro.core.support.property

import sc.tyro.core.support.Clearable
import sc.tyro.core.support.state.EmptySupport
import sc.tyro.core.support.state.ReadOnlySupport
import sc.tyro.core.support.state.RequiredSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
interface InputSupport extends Clearable, EmptySupport, ReadOnlySupport, RequiredSupport {
    String placeholder()

    void value(Object value)
}
