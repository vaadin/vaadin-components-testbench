package com.vaadin.flow.component.common.testbench.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.parallel.BrowserUtil;

public abstract class AbstractIT extends AbstractParallelSauceLabsTest {

    protected String getLogRow(int i) {
        return findElement(By.id("log")).findElements(By.tagName("div")).get(i)
                .getText();
    }

    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowserConfiguration() {
        return Arrays.asList(BrowserUtil.chrome());
    }

    @Before
    public void open() {
        getDriver().get("http://localhost:8080/" + getTestPath());
    }

    private String getTestPath() {
        return getClass().getSimpleName().replaceAll("IT$", "");
    }

}
