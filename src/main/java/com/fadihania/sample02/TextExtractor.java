package com.fadihania.sample02;

import java.io.InputStream;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
// import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;


public class TextExtractor {
    public static String parse(String name) {
        // DocumentParser parser = new ApachePdfBoxDocumentParser();
        DocumentParser parser = new ApacheTikaDocumentParser();
        InputStream inputStream = TextExtractor.class.getClassLoader().getResourceAsStream(name);
        Document document = parser.parse(inputStream);
        return document.text();
    }
}
