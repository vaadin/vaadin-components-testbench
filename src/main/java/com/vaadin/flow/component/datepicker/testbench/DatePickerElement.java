package com.vaadin.flow.component.datepicker.testbench;

import java.time.LocalDate;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-date-picker")
public class DatePickerElement extends TestBenchElement implements HasLabel {

    @Override
    public void clear() {
        setDate(null);
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            setValue("");
        } else {
            setValue(date.toString());
        }
    }

    public LocalDate getDate() {
        String value = getValue();
        if (value.isEmpty()) {
            return null;
        }
        return LocalDate.parse(value);
    }

    protected void setValue(String value) {
        setProperty("value", value);
    }

    protected String getValue() {
        return getPropertyString("value");
    }
}