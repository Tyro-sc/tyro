package sc.tyro.web

import sc.tyro.core.ComponentException
import sc.tyro.core.MetaInfo

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
interface IdProvider {
    List<MetaInfo> metaInfos() throws ComponentException
}