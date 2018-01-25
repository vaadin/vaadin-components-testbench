package com.vaadin.flow.component.combobox.testbench;

import java.util.List;

import org.jboss.weld.exceptions.IllegalArgumentException;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-combo-box")
public class ComboBoxElement extends TestBenchElement implements HasLabel {
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

    public List<String> getPopupSuggestions() {
        openPopup();
        return (List<String>) executeScript("var combobox=arguments[0];" //
                + "return combobox.filteredItems.map(function(item) { return combobox._getItemLabel(item);});",
                this);
    }

    protected TextFieldElement getTextField() {
        return getPropertyElement("$", "input").wrap(TextFieldElement.class);
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
