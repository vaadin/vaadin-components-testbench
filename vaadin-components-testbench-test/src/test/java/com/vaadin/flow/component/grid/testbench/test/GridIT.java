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
package com.vaadin.flow.component.grid.testbench.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.grid.testbench.GridColumnElement;
import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.flow.component.grid.testbench.GridTHTDElement;
import com.vaadin.flow.component.grid.testbench.GridTRElement;
import com.vaadin.testbench.TestBenchElement;

public class GridIT extends AbstractIT {

    private GridElement header;
    private GridElement noHeader;
    private GridElement tenMillion;

    @Before
    public void find() {
        header = $(GridElement.class).id(GridView.HEADER_MULTISELECT);
        noHeader = $(GridElement.class).id(GridView.NO_HEADER);
        tenMillion = $(GridElement.class).id(GridView.TEN_MILLION);
    }

    @Test
    public void scrollToRow() {
        for (int rowIndex : new int[] { 0, 15, 82 }) {
            header.scrollToRow(rowIndex);
            Assert.assertEquals(rowIndex, header.getFirstVisibleRowIndex());
            noHeader.scrollToRow(rowIndex);
            Assert.assertEquals(rowIndex, noHeader.getFirstVisibleRowIndex());
            tenMillion.scrollToRow(rowIndex);
            Assert.assertEquals(rowIndex, tenMillion.getFirstVisibleRowIndex());
        }

        // Requires new grid release
        // tenMillion.scrollToRow(9000000);
        // Assert.assertEquals(9000000, tenMillion.getFirstVisibleRowIndex());
    }

    @Test
    public void getPageSize() {
        Assert.assertEquals(50, header.getPageSize());
        Assert.assertEquals(50, noHeader.getPageSize());
        Assert.assertEquals(50, tenMillion.getPageSize());
    }

    @Test
    public void getFirstVisibleRowIndex() {
        Assert.assertEquals(0, header.getFirstVisibleRowIndex());
        Assert.assertEquals(0, noHeader.getFirstVisibleRowIndex());
        Assert.assertEquals(0, tenMillion.getFirstVisibleRowIndex());

        header.scrollToRow(50);
        noHeader.scrollToRow(50);
        tenMillion.scrollToRow(100);

        Assert.assertEquals(50, header.getFirstVisibleRowIndex());
        Assert.assertEquals(50, noHeader.getFirstVisibleRowIndex());
        Assert.assertEquals(100, tenMillion.getFirstVisibleRowIndex());
    }

    @Test
    public void getRowCount() {
        Assert.assertEquals(100, header.getRowCount());
        Assert.assertEquals(1000, noHeader.getRowCount());
        Assert.assertEquals(10000000, tenMillion.getRowCount());
    }

    @Test
    public void getAllColumns() {
        List<GridColumnElement> headerColumns = header.getAllColumns();
        Assert.assertEquals(4, headerColumns.size());
        List<GridColumnElement> noHeaderColumns = noHeader.getAllColumns();
        Assert.assertEquals(2, noHeaderColumns.size());
        List<GridColumnElement> tenMillionColumns = tenMillion.getAllColumns();
        Assert.assertEquals(2, tenMillionColumns.size());

        Assert.assertEquals("", headerColumns.get(0).getHeaderCell().getText());
        Assert.assertEquals("First name",
                headerColumns.get(1).getHeaderCell().getText());
        Assert.assertEquals("Last name",
                headerColumns.get(2).getHeaderCell().getText());
        Assert.assertEquals("Age",
                headerColumns.get(3).getHeaderCell().getText());

        Assert.assertEquals(headerColumns.get(0), header.getColumn(""));
    }

    @Test
    public void getHeaderCell() {
        Assert.assertEquals("First name", header.getHeaderCell(1).getText());
        Assert.assertEquals("Last name", header.getHeaderCell(2).getText());
        Assert.assertEquals("Age", header.getHeaderCell(3).getText());

        // Header always exists but is hidden
        Assert.assertEquals("", noHeader.getHeaderCell(0).getText());
        Assert.assertEquals("", noHeader.getHeaderCell(1).getText());

        Assert.assertEquals("First name",
                tenMillion.getHeaderCell(0).getText());
        Assert.assertEquals("Last name", tenMillion.getHeaderCell(1).getText());
    }

    @Test
    public void getFooterCell() {
        Assert.assertEquals("First Footer",
                tenMillion.getFooterCell(0).getText());
        Assert.assertEquals("Last Footer",
                tenMillion.getFooterCell(1).getText());

        // Footer always exists but is hidden
        Assert.assertEquals("", header.getFooterCell(0).getText());
        Assert.assertEquals("", header.getFooterCell(1).getText());
        Assert.assertEquals("", header.getFooterCell(2).getText());

        Assert.assertEquals("", noHeader.getFooterCell(0).getText());
        Assert.assertEquals("", noHeader.getFooterCell(1).getText());
    }

    @Test
    public void getCell() {
        Assert.assertEquals("First Name 0", header.getCell(0, 1).getText());
        Assert.assertEquals("First Name 0", noHeader.getCell(0, 0).getText());
        Assert.assertEquals("First Name 0", tenMillion.getCell(0, 0).getText());

        Assert.assertEquals("Last name 20", header.getCell(20, 2).getText());
        Assert.assertEquals("Last name 100",
                noHeader.getCell(100, 1).getText());
        Assert.assertEquals("Last name 1000",
                tenMillion.getCell(1000, 1).getText());
        Assert.assertEquals("Last name 1000000",
                tenMillion.getCell(1000000, 1).getText());
    }

    @Test
    public void getRow() {
        GridTRElement row = header.getRow(5);
        GridColumnElement headerColumn = header.getAllColumns().get(2);
        GridTHTDElement cell = row.getCell(headerColumn);
        Assert.assertEquals("Last name 5", cell.getText());
    }

    @Test
    public void singleSelect() {
        Assert.assertFalse(noHeader.getRow(4).isSelected());
        noHeader.select(4);
        Assert.assertTrue(noHeader.getRow(4).isSelected());
        // https://github.com/vaadin/vaadin-grid-flow/issues/74
        // Assert.assertEquals(
        // "1. Grid 'noheader' selection changed to 'Person [firstName=First
        // Name 4, lastName=Last name 4, age=4]'",
        // getLogRow(0));

        noHeader.select(3);
        Assert.assertFalse(noHeader.getRow(4).isSelected());
        Assert.assertTrue(noHeader.getRow(3).isSelected());
        Assert.assertEquals(
                "2. Grid 'noheader' selection changed to 'Person [firstName=First Name 3, lastName=Last name 3, age=3]'",
                getLogRow(0));

        noHeader.select(3); // NO-OP
        Assert.assertTrue(noHeader.getRow(3).isSelected());
        Assert.assertEquals(
                "2. Grid 'noheader' selection changed to 'Person [firstName=First Name 3, lastName=Last name 3, age=3]'",
                getLogRow(0));

        noHeader.deselect(3);
        // https://github.com/vaadin/vaadin-grid-flow/issues/74
        // Assert.assertFalse(noHeader.getRow(3).isSelected());
        // Assert.assertEquals("3. Grid 'noheader' selection changed to '-'",
        // getLogRow(0));
    }

    @Test
    public void multiSelect() {
        header.select(0);
        Assert.assertTrue(header.getRow(0).isSelected());

        // https://github.com/vaadin/vaadin-grid-flow/issues/74
        // Assert.assertEquals(
        // "1. Grid 'header' selection changed to 'Person [firstName=First Name
        // 0, lastName=Last name 0, age=0]'",
        // getLogRow(0));

        header.select(1);
        Assert.assertTrue(header.getRow(0).isSelected());
        Assert.assertTrue(header.getRow(1).isSelected());
        // https://github.com/vaadin/vaadin-grid-flow/issues/74
        // Assert.assertEquals(
        // "2. Grid 'header' selection changed to 'Person [firstName=First Name
        // 0, lastName=Last name 0, age=0], Person [firstName=First Name 1,
        // lastTName=Last name 0, age=0]'",
        // getLogRow(0));

        header.deselect(0);
        Assert.assertFalse(header.getRow(0).isSelected());
        Assert.assertTrue(header.getRow(1).isSelected());
        // https://github.com/vaadin/vaadin-grid-flow/issues/74
        // Assert.assertEquals(
        // "3. Grid 'header' selection changed to 'Person [firstName=First Name
        // 1, lastName=Last name 1, age=1]'",
        // getLogRow(0));
    }

    @Test
    public void getCellByContents() {
        GridTHTDElement cell = header.getCell("Last name 2");
        Assert.assertEquals("Last name",
                cell.getColumn().getHeaderCell().getText());
        Assert.assertEquals(2, cell.getRow());

        cell = header.getCell("15");
        Assert.assertEquals("Age", cell.getColumn().getHeaderCell().getText());
        Assert.assertEquals(15, cell.getRow());

        cell = noHeader.getCell("First Name 12");
        Assert.assertEquals("", cell.getColumn().getHeaderCell().getText());
        Assert.assertEquals(12, cell.getRow());
    }

    @Test
    public void interactWithComponentsInGrid() {
        GridElement components = $(GridElement.class).id(GridView.COMPONENTS);
        GridTHTDElement cell = components.getCell(0, 0);
        ButtonElement button = cell.$(ButtonElement.class).first();
        button.click();
        Assert.assertEquals("Click on button 'First Name 0'",
                getLogRowWithoutNumber(0));
    }

    @Test
    public void detailsRows() {
        GridElement details = $(GridElement.class).id(GridView.DETAILS);
        GridTHTDElement cell = details.getCell(9, 0);
        cell.click();
        GridTRElement rowElement = cell.getRowElement();
        GridTHTDElement detailsRow = rowElement.getDetailsRow();
        List<TestBenchElement> texts = detailsRow.$("span").all();
        Assert.assertEquals(2, texts.size());
        Assert.assertEquals("First Name 9", texts.get(0).getText());
        Assert.assertEquals("Last name 9", texts.get(1).getText());

        ButtonElement button = detailsRow.$(ButtonElement.class).first();
        button.click();
        Assert.assertEquals(
                "Hello Person [firstName=First Name 9, lastName=Last name 9, age=9]",
                getLogRowWithoutNumber(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void getCellByContentsOutsideView() {
        tenMillion.getCell("Last name 2000");
    }
}
