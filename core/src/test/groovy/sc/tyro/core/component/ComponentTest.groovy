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
package sc.tyro.core.component

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sc.tyro.bundle.Widget
import sc.tyro.core.ComponentException
import sc.tyro.core.MetaDataProvider
import sc.tyro.core.MetaInfo
import sc.tyro.core.Provider
import sc.tyro.core.support.Draggable
import sc.tyro.core.support.MouseSupport

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

/**
 * @author David Avenante
 * @since 1.0.0
 */
@DisplayName("Component Tests")
class ComponentTest {
    private MetaDataProvider meta
    private Provider provider

    @BeforeEach
    void before() {
        provider = mock(Provider)
        meta = mock(MetaDataProvider)
    }

    @Test
    @DisplayName("Should have expected Inheritance")
    void inheritance() {
        assert Component in MouseSupport
        assert Component in Draggable
    }

    @Test
    @DisplayName("Should have identity on Id")
    void identity() {
        Component cmp_1 = new Component()
        cmp_1.metaClass.id = { '1' }
        Component cmp_2 = new Component()
        cmp_2.metaClass.id = { '2' }
        Component cmp_3 = new Component()
        cmp_3.metaClass.id = { '1' }

        Widget cmp_4 = new Widget()
        cmp_4.metaClass.id = { '1' }

        assert cmp_1 != cmp_2 // Same class not same id
        assert cmp_1 == cmp_3  // Same class and same id
        assert cmp_1 != cmp_4  // Different class and same id

        assert cmp_1.hashCode() == '1'.hashCode() // hashCode is based on id
    }

    @Test
    @DisplayName("Should implement toString() based on ClassName and Id")
    void implementToString() {
        Component cmp = new Component(provider, meta)
        when(meta.metaInfo(any())).thenReturn(new MetaInfo(id: '1'))

        assert cmp.toString() == 'Component:1'
    }

    @Test
    @DisplayName("Should be available")
    void available() {
        Component cmp = new Component(provider, meta)

        when(meta.metaInfo(cmp)).thenReturn(new MetaInfo())

        assert cmp.available()

        when(meta.metaInfo(cmp)).thenThrow(new ComponentException(""))

        assert !cmp.available()
    }

    @Test
    @DisplayName("Should implements state: enabled")
    void enabled() {
        Component cmp = new Component(provider, meta)

        when(provider.enabled(cmp)).thenReturn(false)

        assert !cmp.enabled()
    }

    @Test
    @DisplayName("Should implements state: visible")
    void visible() {
        Component cmp = new Component(provider, meta)

        when(provider.visible(cmp)).thenReturn(true)

        assert cmp.visible()
    }

    @Test
    @DisplayName("Should implements contains")
    void contains() {
        Component cmp_1 = new Component(provider, meta)
        Component cmp_2 = new Component(provider, meta)

        when(provider.contains(cmp_1, cmp_2)).thenReturn(true)

        assert cmp_1.contains(cmp_2)
    }

    @Test
    @DisplayName("Should be draggable")
    void draggable() {
        Component cmp_1 = new Component(provider, meta)
        Component cmp_2 = new Component(provider, meta)

        cmp_1.drag().on(cmp_2)

        verify(provider, times(1)).dragAndDrop(cmp_1, cmp_2)
    }

    @Test
    @DisplayName("Should support type coercion")
    void coercion() {
        Component cmp = new Widget()

        cmp as Widget

        Exception ex = assertThrows(IllegalStateException, { cmp as BigDecimal })
        assert ex.message == "Unable to assign instance to class java.math.BigDecimal"
    }

    @Test
    @DisplayName("Should implement toString on MetaInfo")
    void metaInfo() {
        MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

        assert metaInfo.toString() == 'id=id, node=node'
    }
}
