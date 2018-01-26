package com.vaadin.flow.component.textfield.testbench;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.flow.component.common.testbench.HasPlaceholder;
import com.vaadin.testbench.HasStringValueProperty;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-password-field")
public class PasswordFieldElement extends TestBenchElement
        implements HasStringValueProperty, HasLabel, HasPlaceholder {

    public boolean isPasswordVisible() {
        return getPropertyBoolean("passwordVisible");
    }

    public void setPasswordVisible(boolean passwordVisible) {
        if (isPasswordVisible() != passwordVisible) {
            callFunction("_togglePasswordVisibility");
        }
    }

}
