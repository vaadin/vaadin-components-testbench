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

import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.component.common.testbench.test.DataGenerator;
import com.vaadin.flow.component.common.testbench.test.Person;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("Grid")
@Theme(Lumo.class)
public class GridView extends AbstractView {

    public static final String NO_HEADER = "noheader";
    public static final String HEADER_MULTISELECT = "header";
    public static final String TEN_MILLION = "tenmillion";
    public static final String COMPONENTS = "components";

    public GridView() {
        Grid<Person> gridNoHeader = new Grid<>();
        gridNoHeader.setId(NO_HEADER);
        gridNoHeader.setWidth("100%");
        gridNoHeader.addColumn(person -> person.getFirstName());
        gridNoHeader.addColumn(person -> person.getLastName());
        gridNoHeader.addSelectionListener(e -> {
            log("Grid 'noheader' selection changed to '" + getSelection(e)
                    + "'");
        });
        gridNoHeader.setDataProvider(new DataGenerator(1000));

        Grid<Person> gridHeader = new Grid<>();
        gridHeader.setSelectionMode(SelectionMode.MULTI);
        gridHeader.setId(HEADER_MULTISELECT);
        gridHeader.setWidth("100%");
        Column<Person> firstName = gridHeader
                .addColumn(person -> person.getFirstName())
                .setHeader("First name");
        Column<Person> lastName = gridHeader
                .addColumn(person -> person.getLastName())
                .setHeader("Last name");
        gridHeader.addColumn(person -> person.getAge()).setHeader("Age");
        gridHeader.mergeColumns(firstName, lastName).setHeader("Name");

        gridHeader.addSelectionListener(e -> {
            log("Grid 'header' selection changed to '" + getSelection(e) + "'");
        });
        gridHeader.setDataProvider(new DataGenerator(100));

        Grid<Person> tenMillionGrid = new Grid<>();
        tenMillionGrid.setId(TEN_MILLION);
        tenMillionGrid.setWidth("100%");
        firstName = tenMillionGrid.addColumn(person -> person.getFirstName())
                .setHeader("First name").setFooter("First Footer");
        lastName = tenMillionGrid.addColumn(person -> person.getLastName())
                .setHeader("Last name").setFooter("Last Footer");
        tenMillionGrid.mergeColumns(firstName, lastName).setHeader("Name")
                .setFooter("Something");

        tenMillionGrid.addSelectionListener(e -> {
            log("Grid 'tenmillion' selection changed to '" + getSelection(e)
                    + "'");
        });
        tenMillionGrid.setDataProvider(new DataGenerator(10000000));

        Grid<Person> componentGrid = new Grid<>();
        componentGrid.setId(COMPONENTS);
        componentGrid.setWidth("100%");
        componentGrid
                .addColumn(new ComponentRenderer<Button, Person>(person -> {
                    return new Button(person.getFirstName(), e -> {
                        log("Click on button '" + e.getSource().getText()
                                + "'");
                    });
                }));

        componentGrid.addSelectionListener(e -> {
            log("Grid 'componentGrid' selection changed to '" + getSelection(e)
                    + "'");
        });
        componentGrid.setDataProvider(new DataGenerator(10000));

        add(new HorizontalLayout(gridHeader, gridNoHeader, tenMillionGrid,
                componentGrid));
    }

    private String getSelection(SelectionEvent<Grid<Person>, Person> e) {
        return e.getAllSelectedItems().stream().map(Person::toString)
                .collect(Collectors.joining(", "));
    }

}
