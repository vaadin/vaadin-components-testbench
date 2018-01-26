package com.vaadin.flow.component.grid.testbench.test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.component.common.testbench.test.Person;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
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

        add(new HorizontalLayout(gridHeader, gridNoHeader, tenMillionGrid));
    }

    private String getSelection(SelectionEvent<Person> e) {
        return e.getAllSelectedItems().stream().map(Person::toString)
                .collect(Collectors.joining(", "));
    }

    public static class DataGenerator
            extends AbstractBackEndDataProvider<Person, String> {

        private int size;

        public DataGenerator(int size) {
            this.size = size;
        }

        @Override
        protected Stream<Person> fetchFromBackEnd(Query<Person, String> query) {
            int first = query.getOffset();
            int count = query.getLimit();

            System.out.println("Fetching " + first + " - " + (first + count - 1)
                    + " (" + count + " items)");
            Stream<Person> data = IntStream.range(first, first + count)
                    .mapToObj(i -> {
                        return new Person("First Name " + i, "Last name " + i,
                                i);
                    });

            return data;
        }

        @Override
        protected int sizeInBackEnd(Query<Person, String> query) {
            return size;
        }

    }

}
