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
package com.vaadin.flow.component.progressbar.testbench.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.progressbar.testbench.ProgressBarElement;

public class ProgressBarIT extends AbstractIT {

    private ProgressBarElement def;
    private ProgressBarElement hundred;

    @Before
    public void find() {
        def = $(ProgressBarElement.class).id(ProgressBarView.DEFAULT);
        hundred = $(ProgressBarElement.class).id(ProgressBarView.HUNDRED);
    }

    @Test
    public void getValue() throws Exception {
        Assert.assertEquals(7, def.getValue(), 0.001);
        Assert.assertEquals(22, hundred.getValue(), 0.001);
    }

}
