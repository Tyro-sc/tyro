/*
 * Copyright © 2020 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.web.internal

import sc.tyro.core.ComponentException
import sc.tyro.core.MetaDataProvider
import sc.tyro.core.MetaInfo
import sc.tyro.core.component.Component
import sc.tyro.web.IdProvider

import static sc.tyro.core.Config.componentTypes
import static sc.tyro.core.Config.provider
import static sc.tyro.web.internal.WebIdentifiers.identifyingExpression

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
                String identifyingExpr = identifyingExpression(c.class)
                if (!provider.check(info.id, identifyingExpr)) {
                    Class<Component> type = componentTypes.find {
                        provider.check(info.id, identifyingExpression(it))
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
