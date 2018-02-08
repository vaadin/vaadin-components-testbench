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

import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("TextField")
@Theme(Lumo.class)
public class TextFieldView extends AbstractView {

    public static final String LABEL = "text";
    public static final String NOLABEL = "notext";
    public static final String INITIAL_VALUE = "initialvalue";
    public static final String PLACEHOLDER = "placeholder";

    public TextFieldView() {

        TextField textfieldNoLabel = new TextField();
        textfieldNoLabel.setId(NOLABEL);
        textfieldNoLabel.addValueChangeListener(this::onValueChange);

        add(textfieldNoLabel);

        TextField textfieldLabel = new TextField("Label");
        textfieldLabel.setId(LABEL);
        textfieldLabel.addValueChangeListener(this::onValueChange);
        add(textfieldLabel);

        TextField textfieldInitialValue = new TextField("Has an initial value");
        textfieldInitialValue.setId(INITIAL_VALUE);
        textfieldInitialValue.setValue("Initial");
        textfieldInitialValue.addValueChangeListener(this::onValueChange);
        add(textfieldInitialValue);

        TextField textfieldPlaceholder = new TextField("Has a placeholder");
        textfieldPlaceholder.setId(PLACEHOLDER);
        textfieldPlaceholder.setPlaceholder("Text goes here");
        textfieldPlaceholder.addValueChangeListener(this::onValueChange);
        add(textfieldPlaceholder);
    }

    protected void onValueChange(ValueChangeEvent<TextField, String> e) {
        String label = e.getSource().getLabel();
        if (label == null) {
            label = "";
        }
        log("Value of '" + label + "' is now " + e.getValue());
    }

}
