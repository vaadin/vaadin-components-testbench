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
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("EmailField")
@Theme(Lumo.class)
public class EmailFieldView extends AbstractView {

    public static final String LABEL_EAGER = "text";
    public static final String NOLABEL = "notext";
    public static final String INITIAL_VALUE = "initialvalue";
    public static final String PLACEHOLDER = "placeholder";

    public EmailFieldView() {

        EmailField emailFieldNoLabel = new EmailField();
        emailFieldNoLabel.setId(NOLABEL);
        emailFieldNoLabel.addValueChangeListener(this::onValueChange);
        add(emailFieldNoLabel);

        EmailField emailFieldLabel = new EmailField("Label (eager)");
        emailFieldLabel.setValueChangeMode(ValueChangeMode.EAGER);
        emailFieldLabel.setId(LABEL_EAGER);
        emailFieldLabel.addValueChangeListener(this::onValueChange);
        add(emailFieldLabel);

        EmailField emailFieldInitialValue = new EmailField(
                "Has an initial value");
        emailFieldInitialValue.setId(INITIAL_VALUE);
        emailFieldInitialValue.setValue("mail@domain.com");
        emailFieldInitialValue.addValueChangeListener(this::onValueChange);
        add(emailFieldInitialValue);

        EmailField emailFieldPlaceholder = new EmailField(
                "Has a placeholder");
        emailFieldPlaceholder.setId(PLACEHOLDER);
        emailFieldPlaceholder.setPlaceholder("Text goes here");
        emailFieldPlaceholder.addValueChangeListener(this::onValueChange);
        add(emailFieldPlaceholder);
    }

    protected void onValueChange(ComponentValueChangeEvent<EmailField, String> e) {
        String label = e.getSource().getLabel();
        if (label == null) {
            label = "";
        }
        log("Value of '" + label + "' is now " + e.getValue());
    }

}
