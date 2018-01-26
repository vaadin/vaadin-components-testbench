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
package com.vaadin.flow.component.upload.testbench;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-upload")
public class UploadElement extends TestBenchElement {

    public void upload(File file) {
        upload(file, true);
    }

    public void upload(File file, boolean waitForUploadToFinish) {
        if (isMaxFilesReached()) {
            removeFile(0);
        }
        WebElement uploadElement = setLocalFileDetector();
        uploadElement.sendKeys(file.getPath());

        if (waitForUploadToFinish) {
            waitForUploads(60);
        }
    }

    private void waitForUploads(int maxSeconds) {
        Timeouts timeouts = getDriver().manage().timeouts();
        timeouts.setScriptTimeout(maxSeconds, TimeUnit.SECONDS);

        String script = "var callback = arguments[arguments.length - 1];"
                + "var upload = arguments[0];"
                + "window.setTimeout(function() {"
                + "  var inProgress = upload.files.filter(function(file) { return file.uploading;}).length >0;"
                + "  if (!inProgress) callback();" //
                + "}, 500);";
        getCommandExecutor().getDriver().executeAsyncScript(script, this);

    }

    private void removeFile(int i) {
        executeScript(
                "arguments[0]._removeFile(arguments[0].files[arguments[1]])",
                this, i);
    }

    public int getMaxFiles() {
        return getPropertyInteger("maxFiles");
    }

    public boolean isMaxFilesReached() {
        return getPropertyBoolean("maxFilesReached");
    }

    private TestBenchElement setLocalFileDetector() {
        TestBenchElement uploadElement = getUploadElement();

        WebElement realUploadElement = uploadElement;
        while (realUploadElement instanceof WrapsElement) {
            realUploadElement = ((WrapsElement) realUploadElement)
                    .getWrappedElement();
        }
        if (realUploadElement instanceof RemoteWebElement) {
            ((RemoteWebElement) realUploadElement)
                    .setFileDetector(new LocalFileDetector());
        } else {
            throw new IllegalArgumentException("Expected argument of type "
                    + RemoteWebElement.class.getName() + ", received "
                    + realUploadElement.getClass().getName());
        }

        return uploadElement;
    }

    private TestBenchElement getUploadElement() {
        return getPropertyElement("$", "fileInput");
    }

    /**
     * Aborts any upload currently in progress.
     */
    public void abort() {
        executeScript(
                "arguments[0].files.forEach(function(file) { return arguments[0].dispatchEvent(new CustomEvent('file-abort', {detail: {file: file}}));})",
                this);
    }

}
