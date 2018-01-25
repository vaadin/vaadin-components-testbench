package com.vaadin.flow.component.grid.testbench;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-grid")
public class GridElement extends TestBenchElement {

    public void scrollToRow(int row) {
        callFunction("_scrollToIndex", row);
    }

    public int getPageSize() {
        return getPropertyInteger("pageSize");
    }

    public long getFirstVisibleRowIndex() {
        return (long) executeScript(
                "return arguments[0]._firstVisibleIndex+arguments[0]._vidxOffset",
                this);
    }

    public long getRowCount() {
        return getPropertyDouble("_effectiveSize").longValue();
    }

    public GridTHTDElement getCell(int rowIndex, int colIndex) {
        if (!isRowInView(rowIndex)) {
            scrollToRow(rowIndex);
        }

        GridRowElement row = getRow(rowIndex);
        GridColumnElement column = getVisibleColumns().get(colIndex);
        return row.getCell(column);
    }

    private long getLastVisibleRowIndex() {
        // Private for now because this seems to be slightly incorrect
        return (long) executeScript(
                "return arguments[0]._lastVisibleIndex+arguments[0]._vidxOffset",
                this);
    }

    private boolean isRowInView(int rowIndex) {
        // Private for now because this seems to be slightly incorrect
        return (getFirstVisibleRowIndex() <= rowIndex
                && rowIndex <= getLastVisibleRowIndex());
    }

    public GridRowElement getRow(int rowIndex) {
        String script = "var rowsInDom = arguments[0].$.items.children;"
                + "var rowInDom = Array.from(rowsInDom).filter(row => row.index == arguments[1])[0];"
                + "return rowInDom;";
        return ((TestBenchElement) executeScript(script, this, rowIndex))
                .wrap(GridRowElement.class);
    }

    public List<GridColumnElement> getAllColumns() {
        String getVisibleColumnsJS = "return arguments[0]._getColumns().sort((a,b) => a._order - b._order)";
        List<TestBenchElement> elements = (List<TestBenchElement>) executeScript(
                getVisibleColumnsJS, this);
        return elements.stream()
                .map(element -> element.wrap(GridColumnElement.class))
                .collect(Collectors.toList());
    }

    public List<GridColumnElement> getVisibleColumns() {
        String getVisibleColumnsJS = "return arguments[0]._getColumns().filter(column => !column.hidden).sort((a,b) => a._order - b._order)";
        List<TestBenchElement> elements = (List<TestBenchElement>) executeScript(
                getVisibleColumnsJS, this);
        return elements.stream()
                .map(element -> element.wrap(GridColumnElement.class))
                .collect(Collectors.toList());

    }

    public GridTHTDElement getHeaderCell(int columnIndex) {
        return getAllColumns().get(columnIndex).getHeaderCell();
    }

    public TestBenchElement getFooterCell(int columnIndex) {
        return getAllColumns().get(columnIndex).getFooterCell();
    }
}