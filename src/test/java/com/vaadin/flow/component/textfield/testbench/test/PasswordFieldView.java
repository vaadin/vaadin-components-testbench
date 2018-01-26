/*
 * Copyright 2000-2017 Vaadin Ltd.
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

import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("PasswordField")
@Theme(Lumo.class)
public class PasswordFieldView extends AbstractView {

    public static final String LABEL = "text";
    public static final String NOLABEL = "notext";
    public static final String INITIAL_VALUE = "initialvalue";
    public static final String PLACEHOLDER = "placeholder";

    public PasswordFieldView() {

        PasswordField passwordFieldNoLabel = new PasswordField();
        passwordFieldNoLabel.setId(NOLABEL);
        add(passwordFieldNoLabel);

        PasswordField passwordFieldLabel = new PasswordField("Label");
        passwordFieldLabel.setId(LABEL);
        add(passwordFieldLabel);

        PasswordField passwordFieldInitialValue = new PasswordField(
                "Has an initial value");
        passwordFieldInitialValue.setId(INITIAL_VALUE);
        passwordFieldInitialValue.setValue("Initial");
        add(passwordFieldInitialValue);

        PasswordField passwordFieldPlaceholder = new PasswordField(
                "Has a placeholder");
        passwordFieldPlaceholder.setId(PLACEHOLDER);
        passwordFieldPlaceholder.setPlaceholder("Text goes here");
        add(passwordFieldPlaceholder);
    }

}
