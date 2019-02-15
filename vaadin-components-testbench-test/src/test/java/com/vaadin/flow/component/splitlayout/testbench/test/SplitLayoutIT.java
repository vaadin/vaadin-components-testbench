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
package com.vaadin.flow.component.splitlayout.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.splitlayout.testbench.SplitLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;

public class SplitLayoutIT extends AbstractIT {

    private SplitLayoutElement splitLayout;

    @Before
    public void find() {
        splitLayout = $(SplitLayoutElement.class).id(SplitLayoutView.DEFAULT);
    }

    @Test
    public void findInside() throws Exception {
        Assert.assertEquals(3, $(TextFieldElement.class).all().size());
        Assert.assertEquals(2,
                splitLayout.$(TextFieldElement.class).all().size());
    }

    @Test
    public void findSplitter() throws Exception {
        TestBenchElement splitter = splitLayout.getSplitter();
        Assert.assertNotNull(splitter);
    }

}
