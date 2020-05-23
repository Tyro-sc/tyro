package sc.tyro.core.support.property

import sc.tyro.core.component.Item

/**
 * @author David Avenante
 * @since 1.0.0
 */
public interface ItemSupport {
    public List<Item> items()

    public Item item(String value)
}
