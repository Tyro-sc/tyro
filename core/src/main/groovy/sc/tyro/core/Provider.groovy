/**
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
package sc.tyro.core

import sc.tyro.core.component.Component
import sc.tyro.core.input.MouseModifiers

/**
 * @author Mathieu Carbou
 * @since 1.0.0
 */
interface Provider {
    def <T extends Component> T find(Class<T> clazz, By by)

    def <T extends Component>  List<T> findAll(Class<T> clazz, By by)

    def <T extends Component> List<T> findAll(Class<T> clazz)

    List<MetaInfo> metaInfo(By.ByExpression expression)

    void click(Component component, Collection<MouseModifiers> mouseModifiers, Collection<?> keys)

    void mouseOver(Component component)

    void dragAndDrop(Component from, Component to)

    void type(Collection<?> keys)

    boolean enabled(Component component)

    boolean visible(Component component)

    boolean contains(Component parent, Component child)

    // Navigation
    void open(String url)

    void navigateTo(String url)

    void back()

    void forward()

    void refresh()

    String getPageTitle()

    String getUrl()

    // Windows
    void closeWindow(String id)

    Set<String> getWindowIds()

    void switchToWindow(String id)

    // Execution
    String eval(String id, String expr)

    Boolean check(String id, String expr)

    def <T> T getJson(String expression)

    void runScript(String script)

    void registerScripts(String... scripts)
}
