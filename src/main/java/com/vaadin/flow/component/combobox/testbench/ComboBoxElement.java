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
package com.vaadin.flow.component.combobox.testbench;

import java.util.List;

import org.jboss.weld.exceptions.IllegalArgumentException;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.flow.component.common.testbench.HasSelectByText;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-combo-box")
public class ComboBoxElement extends TestBenchElement
        implements HasLabel, HasSelectByText {
    @Override
    public void selectByText(String text) {
        Boolean success = (Boolean) executeScript("var combobox = arguments[0];" //
                + "var text = arguments[1];" //
                + "var matches = combobox.items.filter(item => combobox._getItemLabel(item) == text);"
                + "if (matches.length == 0) {" //
                + "  return false;" //
                + "} else {" //
                + "  var value = combobox._getItemValue(matches[0]);"
                + "  combobox.value = value;" + "  return true;" //
                + "}", this, text);
        if (!success) {
            throw new IllegalArgumentException(
                    "Value '" + text + "' not found in the combobox");
        }
    }

    @Override
    public String getSelectedText() {
        return (String) executeScript("var combobox = arguments[0];" //
                + "var value = combobox.value;" //
                + "if (!value) " //
                + "  return '';" //
                + "else " //
                + "  return combobox._getItemLabel(combobox.items.filter(item => item.key == value)[0])",
                this);
    }

    public void openPopup() {
        callFunction("open");
    }

    public void closePopup() {
        callFunction("close");
    }

    public List<String> getOptions() {
        openPopup();
        return (List<String>) executeScript("var combobox=arguments[0];" //
                + "return combobox.filteredItems.map(function(item) { return combobox._getItemLabel(item);});",
                this);
    }

    public boolean isPopupOpen() {
        return getPropertyBoolean("opened");
    }

    public void setFilter(String filter) {
        setProperty("filter", filter);
    }

    public String getFilter() {
        return getPropertyString("filter");
    }

}
