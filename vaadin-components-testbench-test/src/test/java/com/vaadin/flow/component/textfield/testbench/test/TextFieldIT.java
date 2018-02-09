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
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;

public class TextFieldIT extends AbstractIT {

    private TextFieldElement labelEager;
    private TextFieldElement nolabel;
    private TextFieldElement initialValue;
    private TextFieldElement placeholder;

    @Before
    public void find() {
        labelEager = $(TextFieldElement.class).id(TextFieldView.LABEL_EAGER);
        nolabel = $(TextFieldElement.class).id(TextFieldView.NOLABEL);
        initialValue = $(TextFieldElement.class)
                .id(TextFieldView.INITIAL_VALUE);
        placeholder = $(TextFieldElement.class).id(TextFieldView.PLACEHOLDER);
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

}
