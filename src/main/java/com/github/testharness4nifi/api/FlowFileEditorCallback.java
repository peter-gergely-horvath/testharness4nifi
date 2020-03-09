



package com.github.testharness4nifi.api;

import org.w3c.dom.Document;

/**
 * <p>
 * An interface that allows programmatic access to the contents of a NiFi Flow XML,
 * allowing changes to be performed before it
 * is actually installed to the NiFi instance.</p>
 *
 * <p>
 * <strong>CAUTION: THIS IS AN EXPERIMENTAL API: EXPECT CHANGES!</strong>
 * Efforts will be made to retain backwards API compatibility, but
 * no guarantee is given.
 * </p>
 *
 */
public interface FlowFileEditorCallback {

    /**
     *
     * @param document the document to change (never {@code null})
     * @return the changed document (never {@code null})
     * @throws Exception in case the editing fails
     */
    Document edit(Document document) throws Exception;
}
