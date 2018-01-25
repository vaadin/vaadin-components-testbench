package com.vaadin.flow.component.combobox.testbench.test;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.combobox.testbench.ComboBoxElement;
import com.vaadin.flow.component.common.testbench.test.AbstractIT;

public class ComboBoxIT extends AbstractIT {

    private ComboBoxElement comboBoxWithText;
    private ComboBoxElement comboBoxWithNoText;
    private ComboBoxElement comboBoxWithBeans;

    @Before
    public void find() {
        comboBoxWithText = $(ComboBoxElement.class).id(ComboBoxView.TEXT);
        comboBoxWithNoText = $(ComboBoxElement.class).id(ComboBoxView.NOTEXT);
        comboBoxWithBeans = $(ComboBoxElement.class).id(ComboBoxView.BEANS);
    }

    @Test
    public void getLabel() throws Exception {
        Assert.assertEquals("", comboBoxWithNoText.getLabel());
        Assert.assertEquals("Text", comboBoxWithText.getLabel());
        Assert.assertEquals("Persons", comboBoxWithBeans.getLabel());
    }

    @Test
    public void selectByText() throws Exception {
        Assert.assertEquals("", comboBoxWithNoText.getSelectedText());
        Assert.assertEquals("", comboBoxWithText.getSelectedText());
        Assert.assertEquals("", comboBoxWithBeans.getSelectedText());

        comboBoxWithNoText.selectByText("Item 1");
        Assert.assertEquals("1. ComboBox 'null' value is now Item 1",
                getLogRow(0));
        Assert.assertEquals("Item 1", comboBoxWithNoText.getSelectedText());
        comboBoxWithText.selectByText("Item 18");
        Assert.assertEquals("2. ComboBox 'Text' value is now Item 18",
                getLogRow(0));
        Assert.assertEquals("Item 18", comboBoxWithText.getSelectedText());
        comboBoxWithBeans.selectByText("Doe, John");
        Assert.assertEquals(
                "3. ComboBox 'Persons' value is now Person [firstName=John, lastName=Doe]",
                getLogRow(0));
        Assert.assertEquals("Doe, John", comboBoxWithBeans.getSelectedText());
    }

    @Test
    public void openCloseIsOpenPopup() throws Exception {
        comboBoxWithText.openPopup();
        Assert.assertTrue(comboBoxWithText.isPopupOpen());
        comboBoxWithText.closePopup();
        Assert.assertFalse(comboBoxWithText.isPopupOpen());
    }

    @Test
    public void getPopupSuggestions() throws Exception {
        Assert.assertArrayEquals(
                IntStream.range(0, 20).mapToObj(i -> "Item " + i).toArray(),
                comboBoxWithNoText.getPopupSuggestions().toArray());
        Assert.assertArrayEquals(
                IntStream.range(0, 20).mapToObj(i -> "Item " + i).toArray(),
                comboBoxWithText.getPopupSuggestions().toArray());
        Assert.assertArrayEquals(
                new String[] { "Doe, John", "Johnson, Jeff", "Meyer, Diana" },
                comboBoxWithBeans.getPopupSuggestions().toArray());
    }

    @Test
    public void filter() {
        Assert.assertEquals("", comboBoxWithNoText.getFilter());
        Assert.assertEquals("", comboBoxWithText.getFilter());
        Assert.assertEquals("", comboBoxWithBeans.getFilter());

        comboBoxWithNoText.setFilter("2");
        Assert.assertArrayEquals(new String[] { "Item 2", "Item 12" },
                comboBoxWithNoText.getPopupSuggestions().toArray());
        comboBoxWithText.setFilter("2");
        Assert.assertArrayEquals(new String[] { "Item 2", "Item 12" },
                comboBoxWithText.getPopupSuggestions().toArray());
        comboBoxWithBeans.setFilter("Jo");
        Assert.assertArrayEquals(new String[] { "Doe, John", "Johnson, Jeff" },
                comboBoxWithBeans.getPopupSuggestions().toArray());

    }

}
