package com.vaadin.flow.component.tabs.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.tabs.testbench.TabsElement;

public class TabsIT extends AbstractIT {

    private TabsElement def;

    @Before
    public void find() {
        def = $(TabsElement.class).id(TabsView.DEFAULT);
    }

    @Test
    public void selectTabByIndex() throws Exception {
        Assert.assertEquals(0, def.getSelectedTabIndex());
        def.setSelectedTabIndex(1);
        Assert.assertEquals(1, def.getSelectedTabIndex());
        def.setSelectedTabIndex(0);
        Assert.assertEquals(0, def.getSelectedTabIndex());
    }

    @Test
    public void getSelectedTabElement() throws Exception {
        def.getSelectedTabElement().$(ButtonElement.class).first().click();
        Assert.assertEquals("1. Hello clicked", getLogRow(0));
        def.setSelectedTabIndex(2);
        Assert.assertEquals("Text", def.getSelectedTabElement().getText());
    }

    @Test
    public void getTab() throws Exception {
        Assert.assertEquals(1, def.getTab("Disabled"));
        Assert.assertEquals(2, def.getTab("Text"));
    }

    @Test
    public void isEnabled() throws Exception {
        Assert.assertTrue(def.getTabElement("Text").isEnabled());
        Assert.assertFalse(def.getTabElement("Disabled").isEnabled());

    }
}
