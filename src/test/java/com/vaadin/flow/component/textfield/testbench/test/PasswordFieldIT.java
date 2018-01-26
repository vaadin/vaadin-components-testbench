package com.vaadin.flow.component.textfield.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;

public class PasswordFieldIT extends AbstractIT {

    private PasswordFieldElement label;
    private PasswordFieldElement nolabel;
    private PasswordFieldElement initialValue;
    private PasswordFieldElement placeholder;

    @Before
    public void find() {
        label = $(PasswordFieldElement.class).id(PasswordFieldView.LABEL);
        nolabel = $(PasswordFieldElement.class).id(PasswordFieldView.NOLABEL);
        initialValue = $(PasswordFieldElement.class)
                .id(PasswordFieldView.INITIAL_VALUE);
        placeholder = $(PasswordFieldElement.class)
                .id(PasswordFieldView.PLACEHOLDER);
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

    @Test
    public void passwordVisible() throws Exception {
        Assert.assertFalse(label.isPasswordVisible());
        Assert.assertFalse(nolabel.isPasswordVisible());
        Assert.assertFalse(initialValue.isPasswordVisible());
        Assert.assertFalse(placeholder.isPasswordVisible());

        label.setPasswordVisible(true);
        nolabel.setPasswordVisible(true);
        initialValue.setPasswordVisible(true);
        placeholder.setPasswordVisible(true);

        Assert.assertTrue(label.isPasswordVisible());
        Assert.assertTrue(nolabel.isPasswordVisible());
        Assert.assertTrue(initialValue.isPasswordVisible());
        Assert.assertTrue(placeholder.isPasswordVisible());

        label.setPasswordVisible(false);
        nolabel.setPasswordVisible(false);
        initialValue.setPasswordVisible(false);
        placeholder.setPasswordVisible(false);

        Assert.assertFalse(label.isPasswordVisible());
        Assert.assertFalse(nolabel.isPasswordVisible());
        Assert.assertFalse(initialValue.isPasswordVisible());
        Assert.assertFalse(placeholder.isPasswordVisible());

    }

}
