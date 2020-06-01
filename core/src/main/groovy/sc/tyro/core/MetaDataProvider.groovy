package sc.tyro.core

import sc.tyro.core.component.Component

interface MetaDataProvider {
    MetaInfo metaInfo(Component c)

    List<MetaInfo> metaInfos()
}
