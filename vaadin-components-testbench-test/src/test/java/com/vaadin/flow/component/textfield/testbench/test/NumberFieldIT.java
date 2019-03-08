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
import com.vaadin.flow.component.textfield.testbench.NumberFieldElement;

public class NumberFieldIT extends AbstractIT {

    private NumberFieldElement labelEager;
    private NumberFieldElement nolabel;
    private NumberFieldElement initialValue;
    private NumberFieldElement placeholder;

    @Before
    public void find() {
        labelEager = $(NumberFieldElement.class)
                .id(NumberFieldView.LABEL_EAGER);
        nolabel = $(NumberFieldElement.class).id(NumberFieldView.NOLABEL);
        initialValue = $(NumberFieldElement.class)
                .id(NumberFieldView.INITIAL_VALUE);
        placeholder = $(NumberFieldElement.class)
                .id(NumberFieldView.PLACEHOLDER);
    }

    @Test
    public void getSetValue() throws Exception {
        Assert.assertEquals("", labelEager.getValue());
        Assert.assertEquals("", nolabel.getValue());
        Assert.assertEquals("10", initialValue.getValue());
        Assert.assertEquals("", placeholder.getValue());

        labelEager.setValue("5.0");
        assertStringValue(labelEager, "5.0");

        nolabel.setValue("5.0");
        assertStringValue(nolabel, "5.0");

        initialValue.setValue("5.0");
        assertStringValue(initialValue, "5.0");

        placeholder.setValue("5.0");
        assertStringValue(placeholder, "5.0");
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
