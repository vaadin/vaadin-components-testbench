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
package com.vaadin.flow.component.dialog.testbench;

import org.openqa.selenium.SearchContext;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-dialog")
public class DialogElement extends TestBenchElement {

    public void open() {
        setProperty("opened", true);
    }

    public void close() {
        setProperty("opened", false);
    }

    public boolean isOpen() {
        return getPropertyBoolean("opened");
    }

    @Override
    public SearchContext getContext() {
        // Find child elements inside the overlay, not the dialog element
        return getOverlay();
    }

    private TestBenchElement getOverlay() {
        return getPropertyElement("$", "overlay");
    }

}
