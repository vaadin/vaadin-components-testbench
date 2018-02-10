/*
 * Copyright 2000-2018 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.textfield.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;

public class PasswordFieldIT extends AbstractIT {

    private PasswordFieldElement labelEager;
    private PasswordFieldElement nolabel;
    private PasswordFieldElement initialValue;
    private PasswordFieldElement placeholder;

    @Before
    public void find() {
        labelEager = $(PasswordFieldElement.class)
                .id(PasswordFieldView.LABEL_EAGER);
        nolabel = $(PasswordFieldElement.class).id(PasswordFieldView.NOLABEL);
        initialValue = $(PasswordFieldElement.class)
                .id(PasswordFieldView.INITIAL_VALUE);
        placeholder = $(PasswordFieldElement.class)
                .id(PasswordFieldView.PLACEHOLDER);
    }

    @Test
    public void getSetValue() throws Exception {
        Assert.assertEquals("", labelEager.getValue());
        Assert.assertEquals("", nolabel.getValue());
        Assert.assertEquals("Initial", initialValue.getValue());
        Assert.assertEquals("", placeholder.getValue());

        labelEager.setValue("Foo");
        assertStringValue(labelEager, "Foo");

        nolabel.setValue("Foo");
        assertStringValue(nolabel, "Foo");

        initialValue.setValue("Foo");
        assertStringValue(initialValue, "Foo");

        placeholder.setValue("Foo");
        assertStringValue(placeholder, "Foo");
    }

    @Test
    public void getLabelEager() throws Exception {
        Assert.assertEquals("Label (eager)", labelEager.getLabel());
        Assert.assertEquals("", nolabel.getLabel());
        Assert.assertEquals("Has an initial value", initialValue.getLabel());
        Assert.assertEquals("Has a placeholder", placeholder.getLabel());
    }

    @Test
    public void getPlaceholder() throws Exception {
        Assert.assertEquals("", labelEager.getPlaceholder());
        Assert.assertEquals("", nolabel.getPlaceholder());
        Assert.assertEquals("", initialValue.getPlaceholder());
        Assert.assertEquals("Text goes here", placeholder.getPlaceholder());
    }

    @Test
    public void passwordVisible() throws Exception {
        Assert.assertFalse(labelEager.isPasswordVisible());
        Assert.assertFalse(nolabel.isPasswordVisible());
        Assert.assertFalse(initialValue.isPasswordVisible());
        Assert.assertFalse(placeholder.isPasswordVisible());

        labelEager.setPasswordVisible(true);
        nolabel.setPasswordVisible(true);
        initialValue.setPasswordVisible(true);
        placeholder.setPasswordVisible(true);

        Assert.assertTrue(labelEager.isPasswordVisible());
        Assert.assertTrue(nolabel.isPasswordVisible());
        Assert.assertTrue(initialValue.isPasswordVisible());
        Assert.assertTrue(placeholder.isPasswordVisible());

        labelEager.setPasswordVisible(false);
        nolabel.setPasswordVisible(false);
        initialValue.setPasswordVisible(false);
        placeholder.setPasswordVisible(false);

        Assert.assertFalse(labelEager.isPasswordVisible());
        Assert.assertFalse(nolabel.isPasswordVisible());
        Assert.assertFalse(initialValue.isPasswordVisible());
        Assert.assertFalse(placeholder.isPasswordVisible());

    }

}
