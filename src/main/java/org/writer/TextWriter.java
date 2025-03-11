package org.writer;

import org.content.TextContent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
    private final String path;

    public TextWriter(String path) {
        this.path = path;
    }

    public void write(String title, TextContent textContent) {
        String filePath = path+"/"+title+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(textContent.getText());
        } catch (IOException e) {
            return;
        }
    }
}
