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
