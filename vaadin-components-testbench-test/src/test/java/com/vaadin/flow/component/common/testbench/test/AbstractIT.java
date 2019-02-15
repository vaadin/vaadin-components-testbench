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
package com.vaadin.flow.component.common.testbench.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.testbench.HasStringValueProperty;
import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.BrowserUtil;

public abstract class AbstractIT extends AbstractParallelSauceLabsTest {

    protected String getLogRow(int i) {
        return findElement(By.id("log")).findElements(By.tagName("div")).get(i)
                .getText();
    }

    protected String getLogRowWithoutNumber(int i) {
        return getLogRow(i).replaceFirst(".*\\. ", "");
    }

    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowserConfiguration() {
        String browsers = System.getenv("BROWSERS");
        List<DesiredCapabilities> finalList = new ArrayList<>();
        if (browsers != null) {
            for (String browserName : browsers.split(",")) {
                Browser browser = Browser.valueOf(
                        browserName.toUpperCase(Locale.ENGLISH).trim());
                DesiredCapabilities capabilities = browser
                        .getDesiredCapabilities();
                if (BrowserUtil.isSafari(capabilities)) {
                    capabilities.setVersion("11");
                } else if (BrowserUtil.isIE(capabilities)) {
                    capabilities.setPlatform(Platform.WIN8_1);
                }
                finalList.add(capabilities);
            }
        } else {
            finalList.add(BrowserUtil.chrome());
        }
        return finalList;
    }

    @Before
    public void open() {
        getDriver().get("http://localhost:8080/" + getTestPath());
    }

    private String getTestPath() {
        return getClass().getSimpleName().replaceAll("IT$", "");
    }

    protected <T extends HasStringValueProperty & HasLabel> void assertStringValue(
            T element, String expectedValue) {
        Assert.assertEquals(expectedValue, element.getValue());
        Assert.assertEquals(
                "Value of '" + element.getLabel() + "' is now " + expectedValue,
                getLogRowWithoutNumber(0));
    }

}
