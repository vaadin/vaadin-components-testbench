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
package com.vaadin.flow.component.tabs.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.tabs.testbench.TabsElement;
import com.vaadin.testbench.parallel.BrowserUtil;

public class TabsIT extends AbstractIT {

    private TabsElement def;

    @Before
    public void find() {
        def = $(TabsElement.class).id(TabsView.DEFAULT);
    }

    @Test
    public void selectTabByIndex() throws Exception {
        Assert.assertEquals(0, def.getSelectedTabIndex());
        def.setSelectedTabIndex(1);
        Assert.assertEquals(1, def.getSelectedTabIndex());
        def.setSelectedTabIndex(0);
        Assert.assertEquals(0, def.getSelectedTabIndex());
    }

    @Test
    public void getSelectedTabElement() throws Exception {
        // https://github.com/vaadin/vaadin-tabs-flow/issues/27
        if (BrowserUtil.isEdge(getDesiredCapabilities())
                || BrowserUtil.isFirefox(getDesiredCapabilities())
                || BrowserUtil.isIE(getDesiredCapabilities())) {
            return;
        }
        def.getSelectedTabElement().$(ButtonElement.class).first().click();
        Assert.assertEquals("1. Hello clicked", getLogRow(0));
        def.setSelectedTabIndex(2);
        Assert.assertEquals("Text", def.getSelectedTabElement().getText());
    }

    @Test
    public void getTab() throws Exception {
        Assert.assertEquals(1, def.getTab("Disabled"));
        Assert.assertEquals(2, def.getTab("Text"));
    }

    @Test
    public void isEnabled() throws Exception {
        Assert.assertTrue(def.getTabElement("Text").isEnabled());
        Assert.assertFalse(def.getTabElement("Disabled").isEnabled());

    }
}
