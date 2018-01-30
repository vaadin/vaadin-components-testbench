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
package com.vaadin.flow.component.radiobutton.testbench;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vaadin.flow.component.common.testbench.HasSelectByText;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-radio-group")
public class RadioButtonGroupElement extends TestBenchElement
        implements HasSelectByText {

    public List<String> getOptions() {
        return getRadioButtons().stream().map(RadioButtonElement::getItem)
                .collect(Collectors.toList());
    }

    private List<RadioButtonElement> getRadioButtons() {
        return $(RadioButtonElement.class).all();
    }

    @Override
    public void selectByText(String text) {
        Optional<RadioButtonElement> radioButton = getRadioButtonByText(text);
        if (!radioButton.isPresent()) {
            throw new NoSuchElementException(
                    "No item with text '" + text + "' found");
        }

        radioButton.get().setChecked(true);
    }

    @Override
    public String getSelectedText() {
        Optional<RadioButtonElement> button = getSelectedRadioButton();
        return button.map(RadioButtonElement::getItem).orElse(null);
    }

    private Optional<RadioButtonElement> getSelectedRadioButton() {
        return getRadioButtonByValue(getValue());
    }

    private Optional<RadioButtonElement> getRadioButtonByText(String text) {
        if (text == null) {
            return Optional.empty();
        }
        return getRadioButtons().stream()
                .filter(radioButton -> text.equals(radioButton.getItem()))
                .findFirst();
    }

    private Optional<RadioButtonElement> getRadioButtonByValue(String value) {
        if (value == null) {
            return Optional.empty();
        }
        return getRadioButtons().stream()
                .filter(radioButton -> value.equals(radioButton.getValue()))
                .findFirst();
    }

    private String getValue() {
        return getPropertyString("value");
    }
}
