



package com.github.testharness4nifi.util;

import com.github.testharness4nifi.api.FlowFileEditorCallback;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;

public final class XmlUtils {

    public static void editXml(File inputFile, File outputFile, FlowFileEditorCallback editCallback) {

      try {
          Document document = getFileAsDocument(inputFile);

          document = editCallback.edit(document);

          // save the result
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          transformer.transform(new DOMSource(document), new StreamResult(outputFile));

      } catch (Exception e) {
          throw new RuntimeException("Failed to change XML document: " + e.getMessage(), e);
      }
  }

  public static Document getFileAsDocument(File xmlFile) {
      try(FileInputStream inputStream = new FileInputStream(xmlFile)) {

          DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

          return documentBuilder.parse(new InputSource(inputStream));

      } catch (Exception e) {
          throw new RuntimeException("Failed to parse XML file: " + xmlFile, e);
      }
  }

}