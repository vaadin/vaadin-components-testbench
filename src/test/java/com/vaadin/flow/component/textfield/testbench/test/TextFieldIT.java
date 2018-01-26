package com.vaadin.flow.component.textfield.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;

public class TextFieldIT extends AbstractIT {

    private TextFieldElement label;
    private TextFieldElement nolabel;
    private TextFieldElement initialValue;
    private TextFieldElement placeholder;

    @Before
    public void find() {
        label = $(TextFieldElement.class).id(TextFieldView.LABEL);
        nolabel = $(TextFieldElement.class).id(TextFieldView.NOLABEL);
        initialValue = $(TextFieldElement.class)
                .id(TextFieldView.INITIAL_VALUE);
        placeholder = $(TextFieldElement.class).id(TextFieldView.PLACEHOLDER);
    }

    @Test
    public void getSetValue() throws Exception {
        Assert.assertEquals("", label.getValue());
        Assert.assertEquals("", nolabel.getValue());
        Assert.assertEquals("Initial", initialValue.getValue());
        Assert.assertEquals("", placeholder.getValue());

        label.setValue("Foo");
        nolabel.setValue("Foo");
        initialValue.setValue("Foo");
        placeholder.setValue("Foo");

        Assert.assertEquals("Foo", label.getValue());
        Assert.assertEquals("Foo", nolabel.getValue());
        Assert.assertEquals("Foo", initialValue.getValue());
        Assert.assertEquals("Foo", placeholder.getValue());
    }

    @Test
    public void getLabel() throws Exception {
        Assert.assertEquals("Label", label.getLabel());
        Assert.assertEquals("", nolabel.getLabel());
        Assert.assertEquals("Has an initial value", initialValue.getLabel());
        Assert.assertEquals("Has a placeholder", placeholder.getLabel());
    }

    @Test
    public void getPlaceholder() throws Exception {
        Assert.assertEquals("", label.getPlaceholder());
        Assert.assertEquals("", nolabel.getPlaceholder());
        Assert.assertEquals("", initialValue.getPlaceholder());
        Assert.assertEquals("Text goes here", placeholder.getPlaceholder());
    }

}
