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
package com.vaadin.flow.component.tabs.testbench;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-tabs")
public class TabsElement extends TestBenchElement {

    public void setSelectedTabIndex(int selectedTab) {
        setProperty("selected", selectedTab);
    }

    public int getSelectedTabIndex() {
        return getPropertyInteger("selected");
    }

    public TabElement getSelectedTabElement() {
        return ((TestBenchElement) executeScript(
                "return arguments[0].children[arguments[0].selected];", this))
                        .wrap(TabElement.class);
    }

    public TabElement getTabElement(String text) {
        int index = getTab(text);
        if (index == -1) {
            throw new NoSuchElementException(
                    "No tab with text '" + text + "' found");
        }
        return $(TabElement.class).get(index);
    }

    public int getTab(String text) {
        List<TestBenchElement> children = getPropertyElements("children");
        for (int i = 0; i < children.size(); i++) {
            String tabText = children.get(i).wrap(TabElement.class).getText();
            if (text.equals(tabText)) {
                return i;
            }
        }
        return -1;
    }

}
