package com.vaadin.flow.component.radiobutton.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-radio-button")
public class RadioButtonElement extends TestBenchElement {
    public String getItem() {
        return getPropertyString("firstChild", "textContent");
    }

    public String getValue() {
        return getPropertyString("value");
    }
}
