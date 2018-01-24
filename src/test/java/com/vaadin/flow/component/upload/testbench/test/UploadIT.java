package com.vaadin.flow.component.upload.testbench.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.hamcrest.core.StringContains;
import org.hamcrest.core.StringEndsWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.common.testbench.test.AbstractIT;
import com.vaadin.flow.component.upload.testbench.UploadElement;

public class UploadIT extends AbstractIT {

    private UploadElement upload;

    @Before
    public void find() {
        upload = $(UploadElement.class).first();
    }

    @Test
    public void upload() throws Exception {
        byte[] file1Contents = "This is file 1"
                .getBytes(StandardCharsets.UTF_8);
        byte[] file2Contents = "This is another åäö file"
                .getBytes(StandardCharsets.UTF_8);

        File file1 = createTempFile(file1Contents);

        upload.upload(file1);
        String logRow = getLogRow(0);
        Assert.assertTrue(logRow.endsWith("File " + file1.getName()
                + " of size " + file1Contents.length + " received"));

        File file2 = createTempFile(file2Contents);
        upload.upload(file2);
        logRow = getLogRow(0);
        Assert.assertTrue(logRow.endsWith("File " + file2.getName()
                + " of size " + file2Contents.length + " received"));
    }

    @Test
    // @Ignore("Can't be tested automatically")
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

}
