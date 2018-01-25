package com.vaadin.flow.component.checkbox.testbench;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.flow.component.html.testbench.InputElement;
import com.vaadin.flow.component.html.testbench.LabelElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-checkbox")
public class CheckboxElement extends TestBenchElement implements HasLabel {
    public boolean isChecked() {
        return getPropertyBoolean("checked");
    }

    public void setChecked(boolean checked) {
        setProperty("checked", checked);
    }

    @Override
    public String getLabel() {
        return (String) executeScript(
                "return arguments[0].firstChild ? arguments[0].firstChild.textContent : '';",
                this);
    }

    protected LabelElement getLabelElement() {
        return getPropertyElement("$", "label").wrap(LabelElement.class);
    }

    protected InputElement getInputElement() {
        return $(InputElement.class).attribute("part", "native-checkbox")
                .first();
    }
}
