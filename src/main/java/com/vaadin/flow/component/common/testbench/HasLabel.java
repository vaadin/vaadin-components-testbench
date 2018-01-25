package com.vaadin.flow.component.common.testbench;

import com.vaadin.testbench.HasPropertySettersGetters;

public interface HasLabel extends HasPropertySettersGetters {

    default public String getLabel() {
        String ret = getPropertyString("label");
        if (ret == null) {
            return "";
        } else {
            return ret;
        }
    }
}
