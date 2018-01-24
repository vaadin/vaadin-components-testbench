package com.vaadin.flow.component.common.testbench.test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.TestBenchTestCase;

public class AbstractIT extends TestBenchTestCase {

    protected String getLogRow(int i) {
        return findElement(By.id("log")).findElements(By.tagName("div")).get(i)
                .getText();
    }

    @Before
    public void openTest() {
        setDriver(new ChromeDriver());
        open();
    }

    protected void open() {
        getDriver().get("http://localhost:8080/" + getTestPath());
    }

    @After
    public void teardown() {
        getDriver().quit();
    }

    private String getTestPath() {
        return getClass().getSimpleName().replaceAll("IT$", "");
    }

}
