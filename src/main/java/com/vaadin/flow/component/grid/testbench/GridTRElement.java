package com.vaadin.flow.component.grid.testbench;

import com.vaadin.testbench.TestBenchElement;

public class GridTRElement extends TestBenchElement {

    public GridTHTDElement getCell(GridColumnElement column) {
        TestBenchElement e = (TestBenchElement) executeScript(
                "return Array.from(arguments[0].children)."
                        + "filter(cell => cell._column == arguments[1])[0]",
                this, column);
        return e.wrap(GridTHTDElement.class);
    }

    /**
     * Checks if the row is selected
     *
     * @return <code>true</code> if the row is selected, <code>false</code>
     *         otherwise
     */
    @Override
    public boolean isSelected() {
        return hasAttribute("selected");
    }

    private boolean hasAttribute(String attribute) {
        return (boolean) callFunction("hasAttribute", attribute);
    }

    /**
     * Selects the row if it is not already selected.
     */
    public void select() {
        executeScript("var grid = arguments[0].getRootNode().host;"
                + "grid.selectItem(arguments[0]._item);", this);
    }

    /**
     * Deselects the row if it is selected.
     */
    public void deselect() {
        executeScript("var grid = arguments[0].getRootNode().host;"
                + "grid.deselectItem(arguments[0]._item);", this);
    }
}
