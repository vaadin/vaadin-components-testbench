package com.vaadin.flow.component.notification.testbench;

import org.openqa.selenium.SearchContext;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-notification")
public class NotificationElement extends TestBenchElement {

    public boolean isOpen() {
        return getPropertyBoolean("opened");
    }

    @Override
    public String getText() {
        return getCard().getText();
    }

    @Override
    public SearchContext getContext() {
        return getCard();
    }

    private TestBenchElement getCard() {
        return getPropertyElement("_card");
    }
}