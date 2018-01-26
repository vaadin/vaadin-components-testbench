package com.vaadin.flow.component.tabs.testbench;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-tabs")
public class TabsElement extends TestBenchElement {

    public void setSelectedTabIndex(int selectedTab) {
        setProperty("selected", selectedTab);
    }

    public int getSelectedTabIndex() {
        return getPropertyInteger("selected");
    }

    public TabElement getSelectedTabElement() {
        return ((TestBenchElement) executeScript(
                "return arguments[0].children[arguments[0].selected];", this))
                        .wrap(TabElement.class);
    }

    public TabElement getTabElement(String text) {
        int index = getTab(text);
        if (index == -1) {
            throw new NoSuchElementException(
                    "No tab with text '" + text + "' found");
        }
        return $(TabElement.class).get(index);
    }

    public int getTab(String text) {
        List<TestBenchElement> children = getPropertyElements("children");
        for (int i = 0; i < children.size(); i++) {
            String tabText = children.get(i).wrap(TabElement.class).getText();
            if (text.equals(tabText)) {
                return i;
            }
        }
        return -1;
    }

}
