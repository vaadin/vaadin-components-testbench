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
