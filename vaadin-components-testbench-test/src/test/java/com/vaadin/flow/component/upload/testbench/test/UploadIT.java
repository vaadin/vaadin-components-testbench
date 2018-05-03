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
package com.vaadin.flow.component.upload.testbench.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.hamcrest.core.StringContains;
import org.hamcrest.core.StringEndsWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.upload.testbench.UploadElement;
import com.vaadin.testbench.parallel.BrowserUtil;

public class UploadIT extends AbstractIT {

    private UploadElement upload;

    @Before
    public void find() {
        upload = $(UploadElement.class).first();
    }

    @Override
    public List<DesiredCapabilities> getBrowserConfiguration() {
        List<DesiredCapabilities> browsers = super.getBrowserConfiguration();
        browsers.remove(BrowserUtil.safari());
        return browsers;
    }

    @Test
    public void upload() throws Exception {
        byte[] file1Contents = "This is file 1"
                .getBytes(StandardCharsets.UTF_8);
        byte[] file2Contents = "This is another åäö file"
                .getBytes(StandardCharsets.UTF_8);

        File file1 = createTempFile(file1Contents);

        upload.upload(file1);
        Assert.assertEquals("File " + file1.getName() + " of size "
                + file1Contents.length + " received",
                getLogRowWithoutNumber(0));

        File file2 = createTempFile(file2Contents);
        upload.upload(file2);
        Assert.assertEquals("File " + file2.getName() + " of size "
                + file2Contents.length + " received",
                getLogRowWithoutNumber(0));
    }

    @Test
    @Ignore("Can't be tested automatically")
    // The upload finishes so quickly from localhost. Would need a huge file to
    // be created or throttling support
    public void abortUpload() {
        System.out.println(
                "To test manually, remove @Ignore and set a breakpoint on the abort() line. Then start uploading a huge file after hitting the breakpoint and continue with the test");

        upload.abort();
        String start = getLogRow(1);
        String aborted = getLogRow(0);

        Assert.assertThat(start, StringContains.containsString("Upload of"));
        Assert.assertThat(start, StringEndsWith.endsWith("started"));

        Assert.assertThat(aborted, StringEndsWith.endsWith("failed"));

    }

    private File createTempFile(byte[] contents) throws IOException {
        File tempFile = File.createTempFile("TestFileUpload", ".txt");
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            out.write(contents);
        }
        tempFile.deleteOnExit();
        return tempFile;
    }

    @Override
    public void setDesiredCapabilities(DesiredCapabilities desiredCapabilities) {
        // Disable interactivity check which prevents UploadElement from receiving sendKeys
        // in Firefox https://github.com/mozilla/geckodriver/#mozwebdriverclick
        if (desiredCapabilities.getBrowserName().equals(BrowserType.FIREFOX)) {
            desiredCapabilities.setCapability("moz:webdriverClick", false);
        }

        super.setDesiredCapabilities(desiredCapabilities);
    }

}
