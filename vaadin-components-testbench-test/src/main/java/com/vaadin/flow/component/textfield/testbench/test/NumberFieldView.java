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

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("NumberField")
@Theme(Lumo.class)
public class NumberFieldView extends AbstractView {

    public static final String LABEL_EAGER = "text";
    public static final String NOLABEL = "notext";
    public static final String INITIAL_VALUE = "initialvalue";
    public static final String PLACEHOLDER = "placeholder";

    public NumberFieldView() {

        NumberField numberFielddNoLabel = new NumberField();
        numberFielddNoLabel.setId(NOLABEL);
        numberFielddNoLabel.addValueChangeListener(this::onValueChange);
        add(numberFielddNoLabel);

        NumberField numberFielddLabel = new NumberField("Label (eager)");
        numberFielddLabel.setValueChangeMode(ValueChangeMode.EAGER);
        numberFielddLabel.setId(LABEL_EAGER);
        numberFielddLabel.addValueChangeListener(this::onValueChange);
        add(numberFielddLabel);

        NumberField numberFielddInitialValue = new NumberField(
                "Has an initial value");
        numberFielddInitialValue.setId(INITIAL_VALUE);
        numberFielddInitialValue.setValue(10.0);
        numberFielddInitialValue.addValueChangeListener(this::onValueChange);
        add(numberFielddInitialValue);

        NumberField numberFielddPlaceholder = new NumberField(
                "Has a placeholder");
        numberFielddPlaceholder.setId(PLACEHOLDER);
        numberFielddPlaceholder.setPlaceholder("Text goes here");
        numberFielddPlaceholder.addValueChangeListener(this::onValueChange);
        add(numberFielddPlaceholder);
    }

    protected void onValueChange(ComponentValueChangeEvent<NumberField, Double> e) {
        String label = e.getSource().getLabel();
        if (label == null) {
            label = "";
        }
        log("Value of '" + label + "' is now " + e.getValue());
    }

}
