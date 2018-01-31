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
package com.vaadin.flow.component.button.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;

public class ButtonIT extends AbstractIT {

    private ButtonElement buttonWithText;
    private ButtonElement buttonWithNoText;

    @Before
    public void find() {
        buttonWithText = $(ButtonElement.class).id(ButtonView.TEXT);
        buttonWithNoText = $(ButtonElement.class).id(ButtonView.NOTEXT);
    }

    @Test
    public void click() throws Exception {
        buttonWithNoText.click();
        Assert.assertEquals("1. Button without text clicked", getLogRow(0));
        buttonWithText.click();
        Assert.assertEquals("2. Button with text clicked", getLogRow(0));
    }

    @Test
    public void getText() throws Exception {
        Assert.assertEquals("", buttonWithNoText.getText());
        Assert.assertEquals("Text", buttonWithText.getText());
    }

}
