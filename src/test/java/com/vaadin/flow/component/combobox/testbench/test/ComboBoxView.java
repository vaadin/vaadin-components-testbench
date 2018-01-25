/*
 * Copyright 2000-2017 Vaadin Ltd.
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
package com.vaadin.flow.component.combobox.testbench.test;

import java.util.stream.IntStream;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("ComboBox")
@Theme(Lumo.class)
public class ComboBoxView extends AbstractView {

    public static class Person {
        private String firstName, lastName;

        public Person() {

        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person [firstName=" + firstName + ", lastName=" + lastName
                    + "]";
        }

    }

    public static final String TEXT = "text";
    public static final String NOTEXT = "notext";
    public static final String BEANS = "beans";

    public ComboBoxView() {

        ComboBox<String> comboBoxWithoutText = new ComboBox<>();
        comboBoxWithoutText.setId(NOTEXT);
        comboBoxWithoutText
                .setItems(IntStream.range(0, 20).mapToObj(i -> "Item " + i));
        comboBoxWithoutText.addValueChangeListener(e -> {
            log("ComboBox '" + e.getSource().getLabel() + "' value is now "
                    + e.getValue());
        });
        add(comboBoxWithoutText);

        ComboBox<String> comboBoxWithText = new ComboBox<>("Text");
        comboBoxWithText.setId(TEXT);
        comboBoxWithText
                .setItems(IntStream.range(0, 20).mapToObj(i -> "Item " + i));
        comboBoxWithText.addValueChangeListener(e -> {
            log("ComboBox '" + e.getSource().getLabel() + "' value is now "
                    + e.getValue());
        });
        add(comboBoxWithText);

        ComboBox<Person> comboBoxWithBean = new ComboBox<>("Persons");
        comboBoxWithBean.setId(BEANS);
        comboBoxWithBean.setItemLabelGenerator(
                item -> item.getLastName() + ", " + item.getFirstName());
        comboBoxWithBean.setItems(new Person("John", "Doe"),
                new Person("Jeff", "Johnson"), new Person("Diana", "Meyer"));
        comboBoxWithBean.addValueChangeListener(e -> {
            log("ComboBox '" + e.getSource().getLabel() + "' value is now "
                    + e.getValue());
        });
        add(comboBoxWithBean);
    }

}
