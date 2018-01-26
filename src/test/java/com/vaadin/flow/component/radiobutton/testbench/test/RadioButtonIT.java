package com.vaadin.flow.component.radiobutton.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.radiobutton.testbench.RadioButtonGroupElement;

public class RadioButtonIT extends AbstractIT {

    private RadioButtonGroupElement def;
    private RadioButtonGroupElement preselected;

    @Before
    public void find() {
        def = $(RadioButtonGroupElement.class).id(RadioButtonView.DEFAULT);
        preselected = $(RadioButtonGroupElement.class)
                .id(RadioButtonView.PRESELECTED);
    }

    @Test
    public void getOptions() throws Exception {
        Assert.assertArrayEquals(new String[] { "Item 0", "Item 1", "Item 2",
                "Item 3", "Item 4" }, def.getOptions().toArray());
        Assert.assertArrayEquals(new String[] { "Item 0", "Item 1", "Item 2",
                "Item 3", "Item 4" }, preselected.getOptions().toArray());
    }

    @Test
    public void getSetByText() throws Exception {
        Assert.assertNull(def.getSelectedText());
        Assert.assertEquals("Item 3", preselected.getSelectedText());

        def.selectByText("Item 2");
        Assert.assertEquals("Item 2", def.getSelectedText());
        preselected.selectByText("Item 2");
        Assert.assertEquals("Item 2", preselected.getSelectedText());
    }

}
