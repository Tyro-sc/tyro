package sc.tyro.web.internal

import sc.tyro.core.ComponentException
import sc.tyro.core.MetaDataProvider
import sc.tyro.core.MetaInfo
import sc.tyro.core.component.Component
import sc.tyro.web.IdProvider

import static sc.tyro.core.Config.componentTypes
import static sc.tyro.core.Config.provider

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class CachedMetaData implements MetaDataProvider {
    private MetaInfo metaInfo
    private IdProvider idProvider

    @Override
    MetaInfo metaInfo(Component c) {
        if (!metaInfo) {
            MetaInfo info = idProvider.metaInfos()[0]
            if (c.class != Component) {
                String identifyingExpr = WebIdentifiers.identifyingExpression(c.class)
                if (!(provider.check(info.id, identifyingExpr))) {
                    Class<Component> type = componentTypes.find {
                        provider.check(info.id, WebIdentifiers.identifyingExpression(it))
                    }
                    throw new ComponentException("Expected a ${c.class.simpleName} for component with id '${info.id}', but was: ${type?.simpleName ?: 'unknown'}")
                }
            }
            metaInfo = info
        }
        return metaInfo
    }

    @Override
    List<MetaInfo> metaInfos() {
        idProvider.metaInfos()
    }
}
