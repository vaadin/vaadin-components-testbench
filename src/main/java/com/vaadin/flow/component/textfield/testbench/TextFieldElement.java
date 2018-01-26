package com.vaadin.flow.component.textfield.testbench;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.flow.component.common.testbench.HasPlaceholder;
import com.vaadin.testbench.HasStringValueProperty;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-text-field")
public class TextFieldElement extends TestBenchElement
        implements HasStringValueProperty, HasLabel, HasPlaceholder {

}
