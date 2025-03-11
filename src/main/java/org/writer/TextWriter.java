package org.writer;

import org.content.TextContent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
    private final String writePath;

    public TextWriter(String writePath) {
        this.writePath = writePath;
    }

    public void write(String title, TextContent textContent) {
        String filePath = writePath +"/"+title+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(textContent.getText());
        } catch (IOException e) {
            return;
        }
    }

    public void appendWrite(String title, TextContent textContent) {
        String filePath = writePath +"/"+title+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.write(textContent.getText());
        } catch (IOException e) {
            return;
        }
    }
}
