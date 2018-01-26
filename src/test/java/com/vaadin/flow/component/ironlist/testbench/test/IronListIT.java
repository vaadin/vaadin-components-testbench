package com.vaadin.flow.component.ironlist.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.ironlist.testbench.IronListElement;

public class IronListIT extends AbstractIT {

    private IronListElement def;

    @Before
    public void find() {
        def = $(IronListElement.class).id(IronListView.HUNDRED_THOUSAND);
    }

    @Test
    public void scrollTo() throws Exception {
        def.scrollToRow(1000);
        Assert.assertEquals(1000.0, def.getFirstVisibleRowIndex(), 2);
    }

    @Test
    public void rowCount() {
        Assert.assertEquals(100000, def.getRowCount());
    }

    @Test
    public void firstLastVisibleRow() throws Exception {
        Assert.assertEquals(0, def.getFirstVisibleRowIndex());
        Assert.assertEquals(15, def.getLastVisibleRowIndex());
        Assert.assertTrue(def.isRowInView(0));
        Assert.assertTrue(def.isRowInView(5));
        Assert.assertTrue(def.isRowInView(15));
        Assert.assertFalse(def.isRowInView(105));

        def.scrollToRow(105);
        Assert.assertEquals(105, def.getFirstVisibleRowIndex(), 2);
        Assert.assertEquals(105 + 15, def.getLastVisibleRowIndex(), 2);
        Assert.assertTrue(def.isRowInView(105));
        Assert.assertFalse(def.isRowInView(0));
        Assert.assertFalse(def.isRowInView(1000));
    }

}
