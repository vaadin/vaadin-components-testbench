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
package com.vaadin.flow.component.grid.testbench;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.NoSuchElementException;

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
        GridColumnElement column = getVisibleColumns().get(colIndex);
        return getCell(rowIndex, column);
    }

    public GridTHTDElement getCell(int rowIndex, GridColumnElement column) {
        if (!isRowInView(rowIndex)) {
            scrollToRow(rowIndex);
        }

        GridTRElement row = getRow(rowIndex);
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

    public GridTRElement getRow(int rowIndex) {
        String script = "var rowsInDom = arguments[0].$.items.children;"
                + "var rowInDom = Array.from(rowsInDom).filter(row => row.index == arguments[1])[0];"
                + "return rowInDom;";
        return ((TestBenchElement) executeScript(script, this, rowIndex))
                .wrap(GridTRElement.class);
    }

    /**
     * Gets all columns defined for the grid, including any selection checkbox
     * column.
     *
     * @return a list of grid column elements which can be used to refer to the
     *         given column
     */
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

    /**
     * Gets the column with the given header text.
     * <p>
     * If multiple columns are found with the same header text, returns the
     * first column.
     *
     * @param headerText
     *            the text in the header
     * @return the grid column element for the given column
     * @throws NoSuchElementException
     *             if no column was found
     *
     */
    public GridColumnElement getColumn(String headerText)
            throws NoSuchElementException {
        return getAllColumns().stream().filter(
                column -> headerText.equals(column.getHeaderCell().getText()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "No column with header '" + headerText
                                + "' was found"));
    }

    public GridTHTDElement getHeaderCell(int columnIndex) {
        return getAllColumns().get(columnIndex).getHeaderCell();
    }

    public TestBenchElement getFooterCell(int columnIndex) {
        return getAllColumns().get(columnIndex).getFooterCell();
    }

    public void select(int rowIndex) {
        if (isMultiselect()) {
            getRow(rowIndex).select();
        } else {
            // https://github.com/vaadin/vaadin-grid-flow/issues/75
            setActiveItem(getRow(rowIndex));
        }
    }

    private void setActiveItem(GridTRElement row) {
        executeScript("arguments[0].activeItem=arguments[1]._item", this, row);
    }

    private boolean isMultiselect() {
        return (boolean) executeScript(
                "return arguments[0]._getColumns().filter(col => typeof col.selectAll != 'undefined').length > 0",
                this);
    }

    public void deselect(int rowIndex) {
        getRow(rowIndex).deselect();
    }

}