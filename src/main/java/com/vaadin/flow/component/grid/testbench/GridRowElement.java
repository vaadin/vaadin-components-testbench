package com.vaadin.flow.component.grid.testbench;

import com.vaadin.testbench.TestBenchElement;

public class GridRowElement extends TestBenchElement {

    public GridTHTDElement getCell(GridColumnElement column) {
        TestBenchElement e = (TestBenchElement) executeScript(
                "return Array.from(arguments[0].children)."
                        + "filter(cell => cell._column == arguments[1])[0]",
                this, column);
        return e.wrap(GridTHTDElement.class);
    }
}
