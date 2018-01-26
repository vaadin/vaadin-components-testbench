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
package com.vaadin.flow.component.ironlist.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.ironlist.testbench.IronListElement;

public class IronListIT extends AbstractIT {

    private IronListElement def;

    @Before
    public void find() {
        def = $(IronListElement.class).id(IronListView.HUNDRED_THOUSAND);
    }

    @Test
    public void scrollTo() throws Exception {
        def.scrollToRow(1000);
        Assert.assertEquals(1000.0, def.getFirstVisibleRowIndex(), 2);
    }

    @Test
    public void rowCount() {
        Assert.assertEquals(100000, def.getRowCount());
    }

    @Test
    public void firstLastVisibleRow() throws Exception {
        Assert.assertEquals(0, def.getFirstVisibleRowIndex());
        Assert.assertEquals(15, def.getLastVisibleRowIndex());
        Assert.assertTrue(def.isRowInView(0));
        Assert.assertTrue(def.isRowInView(5));
        Assert.assertTrue(def.isRowInView(15));
        Assert.assertFalse(def.isRowInView(105));

        def.scrollToRow(105);
        Assert.assertEquals(105, def.getFirstVisibleRowIndex(), 2);
        Assert.assertEquals(105 + 15, def.getLastVisibleRowIndex(), 2);
        Assert.assertTrue(def.isRowInView(105));
        Assert.assertFalse(def.isRowInView(0));
        Assert.assertFalse(def.isRowInView(1000));
    }

}
