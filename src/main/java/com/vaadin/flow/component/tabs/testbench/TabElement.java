package com.vaadin.flow.component.tabs.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-tab")
public class TabElement extends TestBenchElement {

    public boolean isEnabled() {
        return !getPropertyBoolean("disabled");
    }

}
