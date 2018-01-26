/*
 * Copyright 2000-2018 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.ironlist.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("iron-list")
public class IronListElement extends TestBenchElement {

    public void scrollToRow(int row) {
        callFunction("scrollToIndex", row);
    }

    public int getFirstVisibleRowIndex() {
        return getPropertyInteger("firstVisibleIndex");
    }

    public int getLastVisibleRowIndex() {
        return getPropertyInteger("lastVisibleIndex");
    }

    public boolean isRowInView(int index) {
        return getFirstVisibleRowIndex() <= index
                && index <= getLastVisibleRowIndex();
    }

    public int getRowCount() {
        return getPropertyInteger("_virtualRowCount");
    }

}