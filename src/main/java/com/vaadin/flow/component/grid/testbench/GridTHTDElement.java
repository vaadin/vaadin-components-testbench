package com.vaadin.flow.component.grid.testbench;

import java.util.List;

import com.vaadin.testbench.TestBenchElement;

public class GridTHTDElement extends TestBenchElement {

    @Override
    public String getText() {
        TestBenchElement slot = getPropertyElement("firstElementChild");
        return getSlotText(slot);
    }

    /**
     * Gets the text content of the elements assigned to the given slot
     * 
     * @param slot
     *            a <code>&lt;slot></code> element
     * @return the combined text content of all elements assigned to the given
     *         slot
     */
    protected static String getSlotText(TestBenchElement slot) {
        List<TestBenchElement> content = (List<TestBenchElement>) slot
                .callFunction("assignedNodes");
        StringBuilder text = new StringBuilder();
        content.forEach(element -> text.append(element.getText()));

        return text.toString();
    }
}
