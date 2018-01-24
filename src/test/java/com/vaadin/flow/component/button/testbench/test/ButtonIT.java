package com.vaadin.flow.component.button.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;

public class ButtonIT extends AbstractIT {

    private ButtonElement buttonWithText;
    private ButtonElement buttonWithNoText;

    @Before
    public void find() {
        buttonWithText = $(ButtonElement.class).id(ButtonView.TEXT);
        buttonWithNoText = $(ButtonElement.class).id(ButtonView.NOTEXT);
    }

    @Test
    public void click() throws Exception {
        buttonWithNoText.click();
        Assert.assertEquals("1. Button without text clicked", getLogRow(0));
        buttonWithText.click();
        Assert.assertEquals("2. Button with text clicked", getLogRow(0));
    }

    @Test
    public void getText() throws Exception {
        Assert.assertEquals("", buttonWithNoText.getText());
        Assert.assertEquals("Text", buttonWithText.getText());
    }

}
