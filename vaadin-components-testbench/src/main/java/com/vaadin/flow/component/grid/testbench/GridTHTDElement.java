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

import org.openqa.selenium.NoSuchElementException;

import com.vaadin.testbench.TestBenchElement;

/**
 * A TestBench element representing a <code>&lt;td&gt;</code> or
 * <code>&lt;th&gt;</code> element in a grid.
 */
public class GridTHTDElement extends TestBenchElement {

    @Override
    public String getText() {
        return (String) executeScript("var cell = arguments[0];"
                + "return Array.from(cell.firstElementChild.assignedNodes()).map(function(node) { return node.textContent;}).join('');",
                this);
        // TestBenchElement slot = getPropertyElement("firstElementChild");
        // return getSlotText(slot);
    }

    /**
     * Gets the text content of the elements assigned to the given slot
     *
     * @param slot
     *            a <code>&lt;slot&gt;</code> element
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

    /**
     * Gets the row index for this grid cell.
     *
     * @return the row index
     */
    public int getRow() {
        return getPropertyInteger("parentElement", "index");
    }

    /**
     * Gets the column for this grid cell.
     *
     * @return the column element
     */
    public GridColumnElement getColumn() {
        Double id = getPropertyDouble("_column", "__generatedTbId");
        GridElement grid = getGrid();
        if (id == null) {
            grid.generatedColumnIdsIfNeeded();
            id = getPropertyDouble("_column", "__generatedTbId");
        }
        if (id == null) {
            throw new NoSuchElementException(
                    "Unable to find column. This should not really happen.");
        }
        return new GridColumnElement(id.longValue(), grid);
    }

    /**
     * Gets the grid containing this element.
     *
     * @return the grid for this element
     */
    public GridElement getGrid() {
        return ((TestBenchElement) executeScript(
                "return arguments[0].getRootNode().host", this))
                        .wrap(GridElement.class);
    }
}
