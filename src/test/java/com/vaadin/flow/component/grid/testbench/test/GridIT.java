package com.vaadin.flow.component.grid.testbench.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.grid.testbench.GridColumnElement;
import com.vaadin.flow.component.grid.testbench.GridElement;

public class GridIT extends AbstractIT {

    private GridElement header;
    private GridElement noHeader;
    private GridElement tenMillion;

    @Before
    public void find() {
        header = $(GridElement.class).id(GridView.HEADER);
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
        Assert.assertEquals(3, headerColumns.size());
        List<GridColumnElement> noHeaderColumns = noHeader.getAllColumns();
        Assert.assertEquals(2, noHeaderColumns.size());
        List<GridColumnElement> tenMillionColumns = tenMillion.getAllColumns();
        Assert.assertEquals(2, tenMillionColumns.size());

        Assert.assertEquals("First name",
                headerColumns.get(0).getHeaderCell().getText());
        Assert.assertEquals("Last name",
                headerColumns.get(1).getHeaderCell().getText());
        Assert.assertEquals("Age",
                headerColumns.get(2).getHeaderCell().getText());
    }

    @Test
    public void getHeaderCell() {
        Assert.assertEquals("First name", header.getHeaderCell(0).getText());
        Assert.assertEquals("Last name", header.getHeaderCell(1).getText());
        Assert.assertEquals("Age", header.getHeaderCell(2).getText());

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
        Assert.assertEquals("First Name 0", header.getCell(0, 0).getText());
        Assert.assertEquals("First Name 0", noHeader.getCell(0, 0).getText());
        Assert.assertEquals("First Name 0", tenMillion.getCell(0, 0).getText());

        Assert.assertEquals("Last name 20", header.getCell(20, 1).getText());
        Assert.assertEquals("Last name 100",
                noHeader.getCell(100, 1).getText());
        Assert.assertEquals("Last name 1000",
                tenMillion.getCell(1000, 1).getText());
        // Requires new vaadin-grid release
        // Assert.assertEquals("Last name 1000000",
        // tenMillion.getCell(1000000, 1).getText());
    }

    @Test
    public void getRow() {
        Assert.assertEquals("Last name 5", header.getRow(5)
                .getCell(header.getAllColumns().get(1)).getText());
    }

}
