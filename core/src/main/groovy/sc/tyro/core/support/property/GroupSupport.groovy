package sc.tyro.core.support.property

import sc.tyro.core.component.Group

/**
 * @author David Avenante
 * @since 1.0.0
 */
public interface GroupSupport {
    List<Group> groups()

    Group group(String value)
}
