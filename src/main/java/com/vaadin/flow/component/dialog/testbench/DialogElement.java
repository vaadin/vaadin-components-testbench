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
