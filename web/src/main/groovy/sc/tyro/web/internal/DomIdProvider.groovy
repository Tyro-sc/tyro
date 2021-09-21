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

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sc.tyro.core.By
import sc.tyro.core.By.ByExpression
import sc.tyro.core.ComponentException
import sc.tyro.core.MetaInfo
import sc.tyro.web.IdProvider

import static sc.tyro.core.Config.provider

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class DomIdProvider implements IdProvider {
    private static Logger LOGGER = LoggerFactory.getLogger(DomIdProvider.class)

    private final ByExpression expression
    private boolean singleElement

    DomIdProvider(ByExpression expression, boolean singleElement) {
        this.expression = jQuery(expression)
        this.singleElement = singleElement
    }

    @Override
    List<MetaInfo> metaInfos() throws ComponentException {
        LOGGER.debug("metaInfos - ${expression}")

        List<MetaInfo> metaInfos = provider.metaInfo(expression)
        if (singleElement) {
            if (metaInfos.size() == 1) return metaInfos
            if (metaInfos.size() == 0) throw new ComponentException("Component defined by expression ${expression} not found.")
            throw new ComponentException("Component defined by expression ${expression} is not unique: got ${metaInfos.size()}")
        }
        return metaInfos
    }

    private static ByExpression jQuery(ByExpression by) {
        By.expression(by.expression.startsWith('$') ? by.expression : ('$(\'' + by.expression + '\')'))
    }
}