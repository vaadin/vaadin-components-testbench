package com.vaadin.flow.component.grid.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-grid-column")
public class GridColumnElement extends TestBenchElement {

    public GridTHTDElement getHeaderCell() {
        return getPropertyElement("_headerCell").wrap(GridTHTDElement.class);
    }

    public GridTHTDElement getFooterCell() {
        return getPropertyElement("_footerCell").wrap(GridTHTDElement.class);
    }
}
