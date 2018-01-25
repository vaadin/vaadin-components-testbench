package com.vaadin.flow.component.dialog.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.dialog.testbench.DialogElement;

public class DialogIT extends AbstractIT {

    private DialogElement dialog;

    @Before
    public void find() {
        dialog = $(DialogElement.class).id(DialogView.THE_DIALOG);
    }

    @Test
    public void openClose() throws Exception {
        Assert.assertTrue(dialog.isOpen());
        dialog.close();
        Assert.assertFalse(dialog.isOpen());
    }

    @Test
    public void notOpenAfterClosedThroughCode() throws Exception {
        Assert.assertTrue(dialog.isOpen());
        dialog.$(ButtonElement.class).first().click();
        Assert.assertFalse(dialog.isOpen());
    }

}
