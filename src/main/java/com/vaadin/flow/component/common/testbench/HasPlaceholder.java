package com.vaadin.flow.component.common.testbench;

import com.vaadin.testbench.HasPropertySettersGetters;

public interface HasPlaceholder extends HasPropertySettersGetters {

    default public String getPlaceholder() {
        String ret = getPropertyString("placeholder");
        if (ret == null) {
            return "";
        } else {
            return ret;
        }
    }
}
