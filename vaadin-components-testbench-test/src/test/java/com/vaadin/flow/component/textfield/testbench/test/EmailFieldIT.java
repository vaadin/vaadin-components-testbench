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
import com.vaadin.flow.component.textfield.testbench.EmailFieldElement;

public class EmailFieldIT extends AbstractIT {

    private EmailFieldElement labelEager;
    private EmailFieldElement nolabel;
    private EmailFieldElement initialValue;
    private EmailFieldElement placeholder;

    @Before
    public void find() {
        labelEager = $(EmailFieldElement.class)
                .id(EmailFieldView.LABEL_EAGER);
        nolabel = $(EmailFieldElement.class).id(EmailFieldView.NOLABEL);
        initialValue = $(EmailFieldElement.class)
                .id(EmailFieldView.INITIAL_VALUE);
        placeholder = $(EmailFieldElement.class)
                .id(EmailFieldView.PLACEHOLDER);
    }

    @Test
    public void getSetValue() throws Exception {
        Assert.assertEquals("", labelEager.getValue());
        Assert.assertEquals("", nolabel.getValue());
        Assert.assertEquals("mail@domain.com", initialValue.getValue());
        Assert.assertEquals("", placeholder.getValue());

        labelEager.setValue("newmail@domain.com");
        assertStringValue(labelEager, "newmail@domain.com");

        nolabel.setValue("newmail@domain.com");
        assertStringValue(nolabel, "newmail@domain.com");

        initialValue.setValue("newmail@domain.com");
        assertStringValue(initialValue, "newmail@domain.com");

        placeholder.setValue("newmail@domain.com");
        assertStringValue(placeholder, "newmail@domain.com");
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
