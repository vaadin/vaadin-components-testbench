package com.vaadin.flow.component.ironlist.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("iron-list")
public class IronListElement extends TestBenchElement {

    public void scrollToRow(int row) {
        callFunction("scrollToIndex", row);
    }

    public int getFirstVisibleRowIndex() {
        return getPropertyInteger("firstVisibleIndex");
    }

    public int getLastVisibleRowIndex() {
        return getPropertyInteger("lastVisibleIndex");
    }

    public boolean isRowInView(int index) {
        return getFirstVisibleRowIndex() <= index
                && index <= getLastVisibleRowIndex();
    }

    public int getRowCount() {
        return getPropertyInteger("_virtualRowCount");
    }

}