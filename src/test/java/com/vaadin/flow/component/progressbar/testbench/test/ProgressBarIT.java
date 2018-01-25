package com.vaadin.flow.component.progressbar.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.progressbar.testbench.ProgressBarElement;

public class ProgressBarIT extends AbstractIT {

    private ProgressBarElement def;
    private ProgressBarElement hundred;

    @Before
    public void find() {
        def = $(ProgressBarElement.class).id(ProgressBarView.DEFAULT);
        hundred = $(ProgressBarElement.class).id(ProgressBarView.HUNDRED);
    }

    @Test
    public void getValue() throws Exception {
        Assert.assertEquals(0.75, def.getValue(), 0.001);
        Assert.assertEquals(22, hundred.getValue(), 0.001);
    }

}
