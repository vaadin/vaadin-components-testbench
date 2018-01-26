package com.vaadin.flow.component.notification.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.notification.testbench.NotificationElement;

public class NotificationIT extends AbstractIT {

    private NotificationElement noText;
    private NotificationElement text;
    private NotificationElement components;

    @Before
    public void find() {
        noText = $(NotificationElement.class).id(NotificationView.NOTEXT);
        text = $(NotificationElement.class).id(NotificationView.TEXT);
        components = $(NotificationElement.class)
                .id(NotificationView.COMPONENTS);
    }

    @Test
    public void getText() throws Exception {
        Assert.assertEquals("", noText.getText());
        Assert.assertEquals("Some text", text.getText());
    }

    @Test
    public void isOpen() throws Exception {
        Assert.assertTrue(noText.isOpen());
        Assert.assertTrue(text.isOpen());
        Assert.assertTrue(components.isOpen());
        components.$(ButtonElement.class).id("close").click();
        Assert.assertFalse(components.isOpen());
    }

    @Test
    public void componentInsideNotification() {
        ButtonElement hello = components.$(ButtonElement.class).id("hello");
        ButtonElement close = components.$(ButtonElement.class).id("close");
        hello.click();
        Assert.assertEquals("1. Hello in notification clicked", getLogRow(0));
        close.click();
        Assert.assertFalse(components.isOpen());
    }

}
