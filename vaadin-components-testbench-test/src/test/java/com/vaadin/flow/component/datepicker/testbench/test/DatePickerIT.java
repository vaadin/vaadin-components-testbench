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
package com.vaadin.flow.component.datepicker.testbench.test;

import java.time.LocalDate;

import org.hamcrest.core.StringEndsWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.datepicker.testbench.DatePickerElement;

public class DatePickerIT extends AbstractIT {

    private DatePickerElement datePickerWithText;
    private DatePickerElement datePickerWithNoText;
    private DatePickerElement datePickerWithPreSelectedDate;

    @Before
    public void find() {
        datePickerWithText = $(DatePickerElement.class).id(DatePickerView.TEXT);
        datePickerWithNoText = $(DatePickerElement.class)
                .id(DatePickerView.NOTEXT);
        datePickerWithPreSelectedDate = $(DatePickerElement.class)
                .id(DatePickerView.PRESELECTED);
    }

    @Test
    public void setGetDate() throws Exception {
        Assert.assertNull(datePickerWithText.getDate());
        Assert.assertNull(datePickerWithNoText.getDate());
        Assert.assertEquals(LocalDate.of(2015, 7, 13),
                datePickerWithPreSelectedDate.getDate());

        LocalDate newDate = LocalDate.of(2018, 3, 22);
        datePickerWithNoText.setDate(newDate);
        Assert.assertThat(getLogRow(0), StringEndsWith
                .endsWith("DatePicker 'null' value changed to 2018-03-22"));
        Assert.assertEquals(newDate, datePickerWithNoText.getDate());

        datePickerWithText.setDate(newDate);
        Assert.assertThat(getLogRow(0), StringEndsWith
                .endsWith("DatePicker 'Text' value changed to 2018-03-22"));
        Assert.assertEquals(newDate, datePickerWithText.getDate());

        datePickerWithPreSelectedDate.setDate(newDate);
        Assert.assertThat(getLogRow(0), StringEndsWith.endsWith(
                "DatePicker 'Pre selected date (2015-07-13)' value changed to 2018-03-22"));
        Assert.assertEquals(newDate, datePickerWithPreSelectedDate.getDate());

    }

    @Test
    public void clear() {
        datePickerWithText.clear();
        Assert.assertNull(datePickerWithText.getDate());
        datePickerWithNoText.clear();
        Assert.assertNull(datePickerWithNoText.getDate());
        datePickerWithPreSelectedDate.clear();
        Assert.assertNull(datePickerWithPreSelectedDate.getDate());

    }

    @Test
    public void getLabel() throws Exception {
        Assert.assertEquals("", datePickerWithNoText.getLabel());
        Assert.assertEquals("Text", datePickerWithText.getLabel());
    }

}
