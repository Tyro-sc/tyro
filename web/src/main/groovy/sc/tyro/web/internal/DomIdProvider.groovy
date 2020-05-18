package sc.tyro.web.internal

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sc.tyro.core.By
import sc.tyro.core.ComponentException
import sc.tyro.core.Config
import sc.tyro.core.MetaInfo
import sc.tyro.web.IdProvider

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
class DomIdProvider implements IdProvider {
    Logger LOGGER = LoggerFactory.getLogger(DomIdProvider.class);

    private final By.ByExpression expression
    private boolean singleElement

    DomIdProvider(By.ByExpression expression, boolean singleElement) {
        this.expression = expression
        this.singleElement = singleElement
    }

    @Override
    List<MetaInfo> metaInfos() throws ComponentException {
        LOGGER.info("metaInfos: ${expression}")

        List<MetaInfo> metaInfos = Config.provider.metaInfo(expression)
        if (singleElement) {
            if (metaInfos.size() == 1) return metaInfos
            if (metaInfos.size() == 0) throw new ComponentException("Component defined by expression ${expression} not found.")
            throw new ComponentException("Component defined by expression ${expression} is not unique: got ${metaInfos.size()}")
        }
        return metaInfos
    }
}
