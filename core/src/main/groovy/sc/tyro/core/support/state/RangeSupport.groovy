package sc.tyro.core.support.state

import sc.tyro.core.support.property.MaximumSupport
import sc.tyro.core.support.property.MinimumSupport
import sc.tyro.core.support.property.StepSupport

/**
 * @author David Avenante
 * @since 1.0.0
 */
public interface RangeSupport extends MaximumSupport, MinimumSupport, StepSupport {
    public boolean inRange()
}
