


package com.github.testharness4nifi.samples;

import java.io.File;

public final class Constants {

    static final File OUTPUT_DIR = new File("./TestHarness4NiFi/NiFiReadTest");

    // NOTE: you will have to have the NiFi distribution ZIP placed into this directory.
    // Its version must be the same as the one referenced in the flow.xml, otherwise it will not work!
    static final File NIFI_ZIP_DIR = new File( System.getProperty("user.dir")).getParentFile();

    static final File FLOW_XML_FILE = new File(NiFiMockFlowTest.class.getResource("/flow.xml").getFile());
}
