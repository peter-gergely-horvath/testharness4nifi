



package com.github.testharness4nifi.samples;

import java.io.File;

final class TestUtils {

    private TestUtils() {
        // no instances allowed
    }

    static File getBinaryDistributionZipFile(File binaryDistributionZipDir) {

        if (!binaryDistributionZipDir.exists()) {
            throw new IllegalStateException("NiFi distribution ZIP file not found at the expected location: "
                    + binaryDistributionZipDir.getAbsolutePath());
        }

        File[] files = binaryDistributionZipDir.listFiles((dir, name) ->
                name.startsWith("nifi-") && name.endsWith("-bin.zip"));

        if (files == null) {
            throw new IllegalStateException(
                    "Not a directory or I/O error reading: " + binaryDistributionZipDir.getAbsolutePath());
        }

        if (files.length == 0) {
            throw new IllegalStateException(
                    "No NiFi distribution ZIP file is found in: " + binaryDistributionZipDir.getAbsolutePath());
        }

        if (files.length > 1) {
            throw new IllegalStateException(
                    "Multiple NiFi distribution ZIP files are found in: " + binaryDistributionZipDir.getAbsolutePath());
        }

        return files[0];
    }
}
