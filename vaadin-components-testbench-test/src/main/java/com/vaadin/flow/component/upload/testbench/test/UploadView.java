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

import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.flow.component.common.testbench.test.AbstractView;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("Upload")
@Theme(Lumo.class)
public class UploadView extends AbstractView {

    public UploadView() {

        Upload upload = new Upload();
        upload.setReceiver((filename, mimetype) -> {
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                }
            };
        });
        upload.addStartedListener(e -> {
            log("Upload of " + e.getFilename() + " of size "
                    + e.getContentLength() + " started");
        });
        upload.addSucceededListener(e -> {
            log("File " + e.getFileName() + " of size " + e.getLength()
                    + " received");
        });
        upload.addFailedListener(e -> {
            log("File " + e.getFileName() + " of size " + e.getLength()
                    + " failed");
        });

        add(upload);
    }

}
